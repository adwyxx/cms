package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 10:52
 */
@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer>, JpaSpecificationExecutor, Serializable {

    /**
     * @description :获取当前类别及其父类别
     * @param id : 当前类别id
     * @author : Leo.W
     * @date : 2019/3/20 15:45
     * @return : 当前类别及其父类别
     **/
    @Query(value = "SELECT * FROM article_categories WHERE FIND_IN_SET(id,getParentCategories(?1)) ORDER BY sort_no ASC",nativeQuery = true)
    List<ArticleCategory> getCurrentAndParents(int id);

    /**
    * @description :获取当前类别及其子类别
    * @param id : 当前类别id
    * @author : Leo.W
    * @date : 2019/3/20 15:45
    * @return : 当前类别及其子类别
    **/
    @Query(value = "SELECT * FROM article_categories WHERE FIND_IN_SET(id,getChildrenCategories(?1)) ORDER BY sort_no ASC",nativeQuery = true)
    List<ArticleCategory> getCurrentAndChildren(int id);
}
