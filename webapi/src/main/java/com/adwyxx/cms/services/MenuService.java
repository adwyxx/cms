package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Menu;

import java.util.ArrayList;

public interface MenuService {
    public ArrayList<Menu> getMenusByUserId(Integer id);

    public ArrayList<Menu> getAll();

}
