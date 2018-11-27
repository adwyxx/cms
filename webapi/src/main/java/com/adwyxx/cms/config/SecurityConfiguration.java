package com.adwyxx.cms.config;

import com.adwyxx.cms.oauth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: Spring Security 配置
 * @Auther: Leo.W
 * @Date: 2018/11/27 16:09
 */
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //自定义UserDetailsService接口实现注入
    @Autowired
    private AuthUserService authUserService;

    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
