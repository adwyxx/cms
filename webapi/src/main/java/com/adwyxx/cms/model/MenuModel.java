package com.adwyxx.cms.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/18 09:32
 */
@Data
public class MenuModel implements Serializable {
    private static final long serialVersionUID = -4464205437093900258L;
    private int id;
    private String name;
    private String iconClass;
    private String route;
    private List<MenuModel> children;

    public MenuModel() {
        this.children = new ArrayList<>();
    }

    public MenuModel(int id, String name, String iconClass, String route) {
        this.id = id;
        this.name = name;
        this.iconClass = iconClass;
        this.route = route;
        this.children = new ArrayList<>();
    }
}
