package com.adwyxx.cms.entities;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/21 15:04
 */
@Data
@Entity(name="carousel_item")
@Proxy(lazy = false)
public class CarouselItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7698837044410727393L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="image_url",nullable = false)
    private String imageUrl;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="descriptions",nullable = false)
    private String descriptions;

    @Column(name="link_url",nullable = false)
    private String linkUrl;

    @Column(name="enabled",nullable = false)
    private boolean enabled;

    @Column(name="sort_no",nullable = false)
    private int sortNo;
}
