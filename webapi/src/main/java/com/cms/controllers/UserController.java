package com.cms.controllers;

import com.cms.entities.User;
import com.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    /**
    * @description:  根据用户ID获取用户信息
    * @param id:用户id
    * @return:  用户信息
    * @author: Leo
    * @date:
    **/
    @RequestMapping(path = "getUser",method = RequestMethod.GET)
    public User getUserById(Integer id)
    {
        return  userRepository.getOne(id);
    }

    /**
    * @Description: 添加用户
    * @param user :用户信息
    * @return:  用户信息
     * @Author: Leo
    * @Date: 2018/11/6
    **/
    @RequestMapping(path = "add",method = RequestMethod.POST)
    public User addUser(User user)
    {
        return  user;
    }
}
