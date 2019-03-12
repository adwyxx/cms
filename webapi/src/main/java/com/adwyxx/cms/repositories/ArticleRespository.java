package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 09:25
 */
@Repository
public interface ArticleRespository extends JpaRepository<Article, Long>, JpaSpecificationExecutor, Serializable {
    @Query(value = "SELECT TOP ?2 * FROM articles WHERE category_id=?1 AND valid_status=1 ORDER BY create_time DESC", nativeQuery = true)
    public List<Article> getTopArticlesByCategory(int categoryId, int topNum);

    @Query(value = "SELECT TOP ?1 * FROM articles WHERE valid_status=1 ORDER BY create_time DESC", nativeQuery = true)
    public List<Article> getTopArticles(int topNum);

    @Modifying
    @Transactional
    @Query(value = "UPDATE articles SET title=?2,category_id=?3,category_path=?4,author=?5,content=?6,modifier=?7,modify_time=now() WHERE id=?1", nativeQuery = true)
    public void update(long id, String title, int categoryId, String categoryPath, String author, String content,String modifier);
}
