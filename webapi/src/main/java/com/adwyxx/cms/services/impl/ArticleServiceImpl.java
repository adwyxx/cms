package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.Article;
import com.adwyxx.cms.entities.ArticleCategory;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.model.TreeNode;
import com.adwyxx.cms.repositories.ArticleCategoryRepository;
import com.adwyxx.cms.repositories.ArticleRespository;
import com.adwyxx.cms.services.ArticleService;
import com.adwyxx.cms.utils.EntityManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 09:05
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRespository respository;

    @Autowired
    private ArticleCategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Article data) {
        respository.save(data);
    }

    @Override
    public void update(Article data) {
        respository.update(data.getId(),data.getTitle(),data.getCategoryId(),data.getCategoryPath(),data.getAuthor(),data.getContent(),data.getModifier());
    }

    @Override
    public void deleteById(long id) {
        respository.deleteById(id);
    }

    @Override
    public List<Article> getTopArticles(int topNum) {
        return respository.getTopArticles(topNum);
    }

    @Override
    public List<Article> getTopArticlesByCategory(int categoryId, int topNum) {
        return respository.getTopArticlesByCategory(categoryId,topNum);
    }

    @Override
    public PaginationDataModel<Article> getPagingData(Map<String, Object> condition) {
        PaginationDataModel<Article> result = new PaginationDataModel<Article> ();

        StringBuilder countSelectSql = new StringBuilder();
        //注意：这里的SQL语句User并不是表，而是表对应的实体
        countSelectSql.append("SELECT COUNT(id) FROM Article WHERE 1=1 ");

        StringBuilder selectSql = new StringBuilder();
        selectSql.append("FROM Article WHERE 1=1 ");

        Map<String,Object> params = new HashMap<>();
        StringBuilder whereSql = new StringBuilder();
        if(condition.containsKey("title") && null != condition.get("title") && !condition.get("title").equals("")){
            // 注意：条件中的displayName并不是表中的字段，而是User实体的属性字段
            whereSql.append(" AND title=:title ");
            params.put("title",condition.get("title"));
        }

        String countSql = countSelectSql.append(whereSql).toString();
        Query countQuery = this.entityManager.createQuery(countSql,Long.class);
        EntityManagerHelper.setParameters(countQuery,params);
        Long count = (Long) countQuery.getSingleResult();

        selectSql.append(whereSql);
        selectSql.append("order by createTime desc");
        String querySql = selectSql.toString();

        Query query = this.entityManager.createQuery(querySql,Article.class);
        EntityManagerHelper.setParameters(query,params);

        int startIndex =0,pageSize=10;

        if(condition.containsKey("pageIndex"))
        {
            startIndex = (int)condition.get("pageIndex");

        }
        if(condition.containsKey("pageSize"))
        {
            pageSize = (int)condition.get("pageSize");
        }
        startIndex = (startIndex-1)*pageSize;

        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        result.setData(query.getResultList());
        result.setTotal(count);
        entityManager.close();
        return result;
    }

    @Override
    public List<TreeNode> getAllCategories() {
        List<ArticleCategory> categories = categoryRepository.findAll();
        List<ArticleCategory> roots = categories.stream()
                .filter(p -> p.getParentId()==0)
                .sorted((c1,c2) -> (c1.getSortNo()-c2.getSortNo()))
                .collect(Collectors.toList());

        List<TreeNode> nodes = new ArrayList<>();
        for(Iterator<ArticleCategory> iter = roots.iterator();iter.hasNext();)
        {
            ArticleCategory category = iter.next();
            TreeNode node = new TreeNode(category.getId(),category.getName(),category.getFullName());
            List<ArticleCategory> children = categories.stream()
                    .filter(c -> c.getParentId()==category.getId())
                    .sorted((c1,c2) -> (c1.getSortNo()-c2.getSortNo()))
                    .collect(Collectors.toList());
            children.forEach(c -> node.addChild(new TreeNode(c.getId(),c.getName(),c.getFullName())));
            nodes.add(node);
        }
        return nodes;
    }
}
