package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByMenuId(Integer menuId);
}
