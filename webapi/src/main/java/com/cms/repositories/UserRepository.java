package com.cms.repositories;

import com.cms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @program: cms
 * @description: 用户接口构建业务方法
 * @author: Leo
 * @create: 2018-11-06 16:59
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor, Serializable {

    @Query(value = "UPDATE users SET password=?1 WHERE id=?2", nativeQuery = true)
    @Modifying
    public void updatePassword(String password,Integer id);

    @Query(value = "SELECT * FROM users WHERE logon_name=?1 AND password=?2", nativeQuery = true)
    public List<User> getByLogonNameAndPassword(String logonName, String password);

    @Query(value = "SELECT COUNT(id) FROM users WHERE logon_name=?1", nativeQuery = true)
    public Integer checkLogonName(String logonName);

}
