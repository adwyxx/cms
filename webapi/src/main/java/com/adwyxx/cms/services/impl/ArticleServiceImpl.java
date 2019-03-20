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
import java.math.BigInteger;
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

        String sqlStart="SELECT * ";
        String sqlCountStart="SELECT COUNT(1) ";
        String sqlEnd="FROM articles WHERE 1=1 ";
        String sqlOrder ="order by create_time desc";
        Map<String,Object> params = new HashMap<>();

        // 文章标题模糊查询
        if(condition.containsKey("title") && null != condition.get("title") && !condition.get("title").equals("")){
            sqlEnd+="AND title LIKE '%"+condition.get("title")+"%' ";
        }
        // 文章类别条件
        if(condition.containsKey("categoryId") && null != condition.get("categoryId") && !condition.get("categoryId").equals("")){
            int categoryId = Integer.parseInt(condition.get("categoryId").toString());
            List<ArticleCategory> categories = this.getCurrentAndChildren(categoryId);
            if(categories.size()>1)
            {
                sqlEnd+="AND category_id IN (";
                for(int i=0;i<categories.size();i++)
                {
                    if(i==0)
                    {
                        sqlEnd += Integer.toString(categories.get(i).getId());
                    }
                    else {
                        sqlEnd += ',' + Integer.toString(categories.get(i).getId());
                    }
                }
                sqlEnd+=")";
            }
            else
            {
                sqlEnd+="AND category_id=:categoryId ";
                params.put("categoryId",categoryId);
            }
        }

        Query query = this.entityManager.createNativeQuery(sqlStart + sqlEnd + sqlOrder, Article.class);
        Query queryCount = this.entityManager.createNativeQuery(sqlCountStart + sqlEnd);

        EntityManagerHelper.setParameters(query,params);
        EntityManagerHelper.setParameters(queryCount,params);

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

        BigInteger count = (BigInteger)queryCount.getSingleResult();
        result.setData(query.getResultList());
        result.setTotal(count.longValue());
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

    @Override
    public Article findById(long id) {
        return respository.getOne(id);
    }

    /**
     * @description :获取当前类别及其父类别
     * @param id : 当前类别id
     * @author : Leo.W
     * @date : 2019/3/20 15:45
     * @return : 当前类别及其父类别
     **/
    @Override
    public List<ArticleCategory> getCurrentAndParents(int id) {
        return categoryRepository.getCurrentAndParents(id);
    }

    /**
     * @description :获取当前类别及其子类别
     * @param id : 当前类别id
     * @author : Leo.W
     * @date : 2019/3/20 15:45
     * @return : 当前类别及其子类别
     **/
    @Override
    public List<ArticleCategory> getCurrentAndChildren(int id) {
        return categoryRepository.getCurrentAndChildren(id);
    }
}
