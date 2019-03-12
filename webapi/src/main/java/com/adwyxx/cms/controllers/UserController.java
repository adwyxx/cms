package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.PaginationDataModel;
import com.adwyxx.cms.model.ResponseModel;
import com.adwyxx.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/sys/users")
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
    @RequestMapping(path = "/getUserById/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Integer id)
    {
        return userService.getByID(id);
    }

    /**
    * @Description: 添加用户
    * @param user :用户信息
    * @return:  用户信息
     * @Author: Leo
    * @Date: 2018/11/6
    **/
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public User addUser(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        return userService.insert(user);
    }

    /**
    * @description :更新用户信息
    * @param user :用户信息
    * @author : Leo.W
    * @date : 2019/1/7 16:13
    **/
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public void update(@RequestBody User user)
    {
        userService.Save(user);
    }

    /**
     * @Description: 根据用户名密码获取用户信息
     * @param logonName :用户登录名
     * @param password :用户密码
     * @return:  用户信息
     * @Author: Leo
     * @Date: 2018/11/6
     **/
    @GetMapping("/getUser")
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
    @GetMapping("/delete/{id}")
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
    @GetMapping("/checkLogonName/{logonName}")
    public boolean checkLogonName(@PathVariable("logonName") String logonName)
    {
        return userService.checkLogonName(logonName);
    }

    /**
    * @description : 用户信息分页查询
    * @param condition : 查询条件
    * @author : Leo.W
    * @date : 2018/12/18 15:45
    * @return : 用户分页信息
    **/
    @PostMapping("/query")
    public PaginationDataModel<User> query(@RequestBody Map<String,Object> condition)
    {
        return userService.getPagingData(condition);
    }
}
