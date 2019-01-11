package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 17:30
 */
public interface MenuRespository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor, Serializable {

}
