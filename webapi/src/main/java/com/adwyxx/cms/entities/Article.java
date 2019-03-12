package com.adwyxx.cms.entities;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: webapi
 * @description: 文章信息
 * @author: Leo
 * @create: 2018-11-09 11:33
 **/
@Data
@Entity
@Table(name="articles")
@Proxy(lazy = false)
public class Article extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 8865219347642176437L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private long id;

    @Column(name="category_id",nullable = false)
    private int categoryId;

    @Column(name="category_path",nullable = false)
    private String categoryPath;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="content",nullable = false)
    private String content;

    @Column(name="hits",nullable = false)
    private int hits;

    @Column(name="valid_status",nullable = false)
    private boolean validStatus;

    @Column(name="modify_time",nullable = false)
    private Date modifyTime;

    @Column(name="modifier",nullable = false)
    private String modifier;

    public Article()
    {
        this.hits=0;

    }
}
