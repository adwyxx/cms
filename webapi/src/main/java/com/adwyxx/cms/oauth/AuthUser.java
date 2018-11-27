package com.adwyxx.cms.oauth;

import com.adwyxx.cms.entities.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description: 权限认证用户信息，实现UserDetails接口
 * @Auther: Leo.W
 * @Date: 2018/11/26 14:27
 */
@Data
public class AuthUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1185545284958729694L;

    //自定已属性:用户ID
    private Integer id;
    //实现接口属性:用户名
    private String username;
    //实现接口属性:密码
    private String password;
    //自定义属性:用户角色列表
    private ArrayList<Role> roleList;

    public AuthUser()
    {
        this.roleList= new ArrayList<Role>();
    }

    public AuthUser(Integer id,String username,String password)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.roleList= new ArrayList<Role>();
    }

    /**
    * @description :获取用户权限
    * @author : Leo.W
    * @date : 2018/11/27 14:23
    * @return :
    **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
    * @description :账户是未过期,过期无法验证
    * @author : Leo.W
    * @date : 2018/11/27 14:21
    * @return : 账户是未过期
    **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
    * @description :用户是否未被锁定,锁定的用户无法进行身份验证
    * @author : Leo.W
    * @date : 2018/11/27 14:22
    * @return : 用户是否未被锁定
    **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
    * @description :用户的凭据(密码)是否没有过期,过期的凭据防止认证
    * @author : Leo.W
    * @date : 2018/11/27 14:23
    * @return :用户的凭据(密码)是否没有过期
    **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
    * @description :用户是否是启用状态,禁用的用户不能身份验证
    * @author : Leo.W
    * @date : 2018/11/27 14:23
    * @return :用户是否是启用状态
    **/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
