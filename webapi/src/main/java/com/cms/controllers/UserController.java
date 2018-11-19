package com.cms.controllers;

import com.cms.entities.User;
import com.cms.model.ResponseResultModel;
import com.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    /**
    * @description:  根据用户ID获取用户信息
    * @param id:用户id
    * @return:  用户信息
    * @author: Leo
    * @date: 2018/11/6
    **/
    @RequestMapping(path = "getUserById/{id}",method = RequestMethod.GET)
    public ResponseResultModel getUserById(@PathVariable("id") Integer id)
    {
        User user = userService.getByID(id);
        ResponseResultModel model = new ResponseResultModel();
        model.setStatus("sccess").setData(user);
        return model;
    }

    /**
    * @Description: 添加用户
    * @param user :用户信息
    * @return:  用户信息
     * @Author: Leo
    * @Date: 2018/11/6
    **/
    @RequestMapping(path = "add",method = RequestMethod.POST)
    public User addUser(@RequestBody User user)
    {
        return userService.insert(user);
    }

    /**
     * @Description: 根据用户名密码获取用户信息
     * @param logonName :用户登录名
     * @param password :用户密码
     * @return:  用户信息
     * @Author: Leo
     * @Date: 2018/11/6
     **/
    @GetMapping("getUser")
    public User getUser(@RequestParam("logonName") String logonName,@RequestParam("password") String password)
    {
        return userService.getByLogonNameAndPassword(logonName,password);
    }

    /**
      * @description: 根据用户id删除用户
      * @param id:用户ID
      * @author Leo.W
      * @date 2018/11/13 17:19
     **/
    @GetMapping("delete/{id}")
    public void deleteById(@PathVariable("id") Integer id)
    {
        userService.deleteById(id);
    }

    /**
    * @description : 验证用户登录名是否存在
    * @param logonName: 用户登录名
    * @author : Leo.W
    * @date : 2018/11/13 17:32
    * @return : 用户登录名是否存在
    **/
    @GetMapping("checkLogonName/{logonName}")
    public boolean checkLogonName(@PathVariable("logonName") String logonName)
    {
        return userService.checkLogonName(logonName);
    }
}
