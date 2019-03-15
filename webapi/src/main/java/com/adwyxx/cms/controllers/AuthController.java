package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.AccessToken;
import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.model.ResponseModel;
import com.adwyxx.cms.model.ResponseStatus;
import com.adwyxx.cms.services.UserService;
import com.adwyxx.cms.utils.Md5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 14:57
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

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

                    result.setStatus(HttpStatus.UNAUTHORIZED);
                    result.setMessage("密码错误！");
                    return result;
                }
                else
                {
                    AccessToken token = userService.longOn(user);
                    if(token==null)
                    {
                        result.setStatus(HttpStatus.UNAUTHORIZED);
                        result.setMessage("生成Token失败！");
                        return  result;
                    }
                    /*
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",user.getId());
                    map.put("name",user.getDisplayName());
                    map.put("logonName",user.getLogonName());
                    map.put("mobile",user.getMobile());
                    map.put("email",user.getEmail());
                    map.put("token",token.getToken());
                    */
                    result.setData("Bearer "+token.getToken());
                    return result;
                }
            }
            else
            {
                result.setStatus(HttpStatus.UNAUTHORIZED);
                result.setMessage("用户名不存在！");
                return result;
            }
        }
        catch (Exception e)
        {
            result.setStatus(HttpStatus.BAD_REQUEST);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/userinfo")
    public  ResponseModel getUserByToken(HttpServletRequest request)
    {
        ResponseModel model = new ResponseModel();
        try
        {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(token==null || token.isEmpty())
            {
                model.setStatus(HttpStatus.UNAUTHORIZED);
                model.setMessage("请重新登录");
            }
            token=token.split(" ")[1];
            User user = userService.findByToken(token);
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("name",user.getDisplayName());
            map.put("logonName",user.getLogonName());
            map.put("mobile",user.getMobile());
            map.put("email",user.getEmail());
            model.setData(map);
            return model;
        }
        catch (Exception e)
        {
            model.setStatus(HttpStatus.BAD_REQUEST);
            model.setMessage(e.getMessage());
        }
        return model;
    }
}
