package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 10:52
 */
@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer>, JpaSpecificationExecutor, Serializable {

}
