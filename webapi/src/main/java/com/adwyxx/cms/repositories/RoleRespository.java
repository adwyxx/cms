package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/9 16:53
 */
public interface RoleRespository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor, Serializable {
    @Query(value = "SELECT COUNT(id) FROM roles WHERE role_name=?1", nativeQuery = true)
    public Integer checkName(String roleName);
}
