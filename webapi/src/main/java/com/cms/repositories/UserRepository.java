package com.cms.repositories;

import com.cms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: cms
 * @description: 用户接口构建业务方法
 * @author: Leo
 * @create: 2018-11-06 16:59
 **/
public interface UserRepository extends JpaRepository<User, Integer> {

}
