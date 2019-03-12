package com.adwyxx.cms.entities;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/12 10:52
 */
@Data
@Entity
@Table(name="article_categories")
@Proxy(lazy = false)
public class ArticleCategory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3892186249618664577L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="parent_id",nullable = false)
    private int parentId;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="full_name",nullable = false)
    private String fullName;

    @Column(name="sort_no",nullable = false)
    private int sortNo;
}
