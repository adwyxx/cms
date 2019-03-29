package com.adwyxx.cms;

import com.adwyxx.cms.model.TreeNode;
import com.adwyxx.cms.services.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAllCategoriesTest()
    {
        List<TreeNode> nodes = articleService.getAllCategories();
        nodes.forEach(System.out::println);
    }
}
