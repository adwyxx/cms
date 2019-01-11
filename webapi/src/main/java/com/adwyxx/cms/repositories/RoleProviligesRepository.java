package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.RoleProviliges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 17:32
 */
public interface RoleProviligesRepository extends JpaRepository<RoleProviliges, Integer>, JpaSpecificationExecutor, Serializable {

}
