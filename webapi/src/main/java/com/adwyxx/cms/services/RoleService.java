package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Role;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.model.TreeNode;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface RoleService {

    void add(Role role);

    void update(Role role);

    void delete(Integer id);

    List<Role> getRolesByMenuId(Integer menuId);

    boolean checkRoleName(String roleName);

    PaginationDataModel<Role> getPagingData(Map<String, Object> condition);

    List<TreeNode> getRoleProviliges(Integer roleId);
}
