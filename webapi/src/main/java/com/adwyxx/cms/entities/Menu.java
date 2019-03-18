package com.adwyxx.cms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "menus")
@Proxy(lazy = false)
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5076402248421521224L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="parent_id",nullable = false)
    private int parentId;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="route",nullable = false)
    private String route;

    @Column(name="icon_class",nullable = false)
    private String iconClass;

    @Column(name="sort_no",nullable = false)
    private int sortNo;

}
