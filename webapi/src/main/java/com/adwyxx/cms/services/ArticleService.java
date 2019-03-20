package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Article;
import com.adwyxx.cms.entities.ArticleCategory;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.model.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 09:02
 */
public interface ArticleService {
    void add(Article data);

    void update(Article data);

    void deleteById(long id);

    List<Article> getTopArticles(int topNum);

    List<Article> getTopArticlesByCategory(int categoryId, int topNum);

    PaginationDataModel<Article> getPagingData(Map<String, Object> condition);

    List<TreeNode> getAllCategories();

    Article findById(long id);

    List<ArticleCategory> getCurrentAndParents(int id);

    List<ArticleCategory> getCurrentAndChildren(int id);
}
