package com.cms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User extends BaseEntity {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //登录名
    @Column(name="logon_name",nullable = false)
    private String logonName;
    //显示姓名
    @Column(name="display_name")
    private String displayName;
    //密码
    @Column(name="password",nullable = false)
    private String password;
    //Email
    @Column(name="email")
    private String email;
    //Mobile
    @Column(name="mobile")
    private String mobile;
    //最后登录时间
    @Column(name="last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    //最后登出时间
    @Column(name="last_logoff_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogoffTime;
}
