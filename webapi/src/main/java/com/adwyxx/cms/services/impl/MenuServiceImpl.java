package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.Menu;
import com.adwyxx.cms.model.MenuModel;
import com.adwyxx.cms.repositories.MenuRespository;
import com.adwyxx.cms.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/18 09:38
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRespository respository;

    @Override
    public List<Menu> getMenusByUserId(int id) {
        return respository.getByUserId(id);
    }

    @Override
    public List<Menu> getAll() {
        return respository.findAll(new Sort(Sort.Direction.ASC,"sortNo"));
    }


    @Override
    public List<MenuModel> getUserProviliges(int userId) {
        List<Menu> menus = this.getMenusByUserId(userId);
        List<MenuModel> result = new ArrayList<>();
        List<Menu> roots = menus.stream().filter(m->m.getParentId() == 0).sorted((m1,m2)-> (m1.getSortNo()-m2.getSortNo())).collect(Collectors.toList());
        for(Menu menu:roots)
        {
            MenuModel node = new MenuModel(menu.getId(),menu.getName(),menu.getIconClass(),menu.getRoute());
            List<Menu> items = menus.stream().filter(m->m.getParentId() == menu.getId()).sorted((m1,m2)-> (m1.getSortNo()-m2.getSortNo())).collect(Collectors.toList());
            for(Menu item:items)
            {
                MenuModel child = new MenuModel(item.getId(),item.getName(),item.getIconClass(),item.getRoute());
                node.getChildren().add(child);
            }
            result.add(node);
        }
        return result;
    }
}
