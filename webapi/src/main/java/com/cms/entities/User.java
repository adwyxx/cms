package com.cms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="users")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User implements Serializable {

    private static final long serialVersionUID = -8253289635265741443L;

    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
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

    //创建时间
    @Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //创建人
    @Column(name="creator")
    private String creator;


    public User()
    {

    }

    public User(Integer id,String logonName,String displayName,String password,String email,String mobile)
    {
        this.id=id;
        this.logonName=logonName;
        this.displayName=displayName;
        this.password=password;
        this.email=email;
        this.mobile=mobile;
    }
}
