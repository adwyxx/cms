package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.AccessToken;
import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.ResponseModel;
import com.adwyxx.cms.model.ResponseStatus;
import com.adwyxx.cms.services.UserService;
import com.adwyxx.cms.utils.Md5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 14:57
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseModel login(@RequestParam("username") String username, @RequestParam("password")String password)
    {
        ResponseModel result = new ResponseModel();
        try{
            password = Md5Helper.EncoderByMd5(password);
            if(userService.checkLogonName(username))
            {
                User user = userService.getByLogonNameAndPassword(username,password);
                if(user==null)
                {
                    result.setStatus(ResponseStatus.LOGIN_FAIL);
                    result.setMessage("密码错误！");
                    return result;
                }
                else
                {
                    AccessToken token = userService.longOn(user);
                    if(token==null)
                    {
                        result.setStatus(ResponseStatus.LOGIN_FAIL);
                        result.setMessage("生成Token失败！");
                        return  result;
                    }
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",user.getId());
                    map.put("name",user.getDisplayName());
                    map.put("logonName",user.getLogonName());
                    map.put("mobile",user.getMobile());
                    map.put("email",user.getEmail());
                    map.put("token",token.getToken());
                    result.setData(map);
                    return result;
                }
            }
            else
            {
                result.setStatus(ResponseStatus.LOGIN_FAIL);
                result.setMessage("用户名不存在！");
                return result;
            }
        }
        catch (Exception e)
        {
            result.setStatus(ResponseStatus.SYSTEM_ERROR);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
