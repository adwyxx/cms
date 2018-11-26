package com.adwyxx.cms.oauth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: Security User Entity
 * @Auther: Leo.W
 * @Date: 2018/11/26 14:27
 */
@Data
public class AuthUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1185545284958729694L;

    private String username;
    private String password;

    public AuthUser()
    {}

    public AuthUser(String username,String password)
    {
        this.username=username;
        this.password=password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
