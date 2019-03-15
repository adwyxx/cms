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
 * @Date: 2019/3/12 08:59
 */
@RestController
@RequestMapping("/sys/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @RequestMapping(path="/add",method = RequestMethod.POST)
    public void add(@RequestBody Article data) {
        //data.setContent(StringEscapeUtils.escapeHtml4(data.getContent()));
        service.add(data);
    }

    @RequestMapping(path="/update",method = RequestMethod.POST)
    public void update(@RequestBody Article data) {
        service.update(data);
    }

    @RequestMapping(path="/deleteById/{id}",method = RequestMethod.POST)
    public void deleteById(@PathVariable("id") long id) {
        service.deleteById(id);
    }

    @RequestMapping(path="/getTopArticles/{topNum}",method = RequestMethod.POST)
    public List<Article> getTopArticles(@PathVariable("topNum") int topNum) {
        return service.getTopArticles(topNum);
    }

    @RequestMapping(path="/getTopArticlesByCategory/{categoryId}/{topNum}",method = RequestMethod.POST)
    public List<Article> getTopArticlesByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("topNum") int topNum) {
        return service.getTopArticlesByCategory(categoryId,topNum);
    }

    @RequestMapping(path="/query",method = RequestMethod.POST)
    public PaginationDataModel<Article> getPagingData(@RequestBody Map<String, Object> condition) {
        return service.getPagingData(condition);
    }

    @GetMapping("/getAllCategories")
    public List<TreeNode> getAllCategories()
    {
        return service.getAllCategories();
    }
}
