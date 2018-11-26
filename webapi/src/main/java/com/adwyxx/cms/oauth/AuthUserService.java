package com.adwyxx.cms.oauth;

import com.adwyxx.cms.entities.User;
import com.adwyxx.cms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description: Security User Service
 * @Auther: Leo.W
 * @Date: 2018/11/26 14:25
 */
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthUser loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getByLogonName(s);
        if(user==null)
        {
            throw new UsernameNotFoundException("用户名不存在");
        }
        AuthUser authUser = new AuthUser(user.getLogonName(),user.getPassword());

        return authUser;
    }
}
