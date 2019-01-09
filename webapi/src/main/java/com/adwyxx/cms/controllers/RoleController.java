package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.Role;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/9 17:02
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService service;

    @PostMapping("/add")
    public void add(@RequestBody Role role)
    {
        this.service.add(role);
    }

    @PostMapping("/update")
    public void update(@RequestBody Role role)
    {
        this.service.add(role);
    }

    @PostMapping("/delete/{id}")
    public void add(@PathVariable("id") Integer id)
    {
        this.service.delete(id);
    }

    @GetMapping("/checkName/{name}")
    public boolean checkName(@PathVariable("name") String name)
    {
        return this.service.checkRoleName(name);
    }

    @PostMapping("/query")
    public PaginationDataModel<Role> query(@RequestBody Map<String,Object> condition)
    {
        return service.getPagingData(condition);
    }
}
