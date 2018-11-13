package com.cms.entities;

import javax.persistence.*;

/**
 * @program: webapi
 * @description: 文章信息
 * @author: Leo
 * @create: 2018-11-09 11:33
 **/
@Entity
@Table(name="articles")
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 2596000399917960335L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private long id;

    @Column(name="category_id",nullable = false)
    private Integer categoryId;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="content",nullable = false)
    private String content;

    @Column(name="hits",nullable = false)
    private Integer hits;

    public Article()
    {
        this.hits=0;

    }
}
