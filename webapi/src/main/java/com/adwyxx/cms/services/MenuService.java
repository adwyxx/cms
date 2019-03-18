package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Menu;
import com.adwyxx.cms.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public interface MenuService {
    public List<Menu> getMenusByUserId(int id);

    public List<Menu> getAll();

    public List<MenuModel> getUserProviliges(int userId);

}
