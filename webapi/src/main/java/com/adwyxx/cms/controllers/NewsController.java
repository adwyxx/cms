package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.Article;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.model.TreeNode;
import com.adwyxx.cms.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/18 14:20
 */
@RestController
@RequestMapping("/news")
public class NewsController  {

    @Autowired
    private ArticleService service;

    @RequestMapping(path="/news/{id}",method = RequestMethod.GET)
    public Article findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @RequestMapping(path="/topallnews/{topNum}",method = RequestMethod.POST)
    public List<Article> getTopArticles(@PathVariable("topNum") int topNum) {
        return service.getTopArticles(topNum);
    }

    @RequestMapping(path="/topcategorynews/{categoryId}/{topNum}",method = RequestMethod.POST)
    public List<Article> getTopArticlesByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("topNum") int topNum) {
        return service.getTopArticlesByCategory(categoryId,topNum);
    }

    @GetMapping("/categories")
    public List<TreeNode> getAllCategories()
    {
        return service.getAllCategories();
    }

    @PostMapping("/query")
    public PaginationDataModel<Article> query(@RequestBody Map<String, Object> condition)
    {
        return service.getPagingData(condition);
    }
}
