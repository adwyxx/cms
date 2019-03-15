package com.adwyxx.cms.entities;

import com.adwyxx.cms.utils.TokenUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/14 15:22
 */
@Data
@Entity
@Table(name="access_token")
@NoArgsConstructor
public class AccessToken implements Serializable {

    private static final long serialVersionUID = -1187427042834243698L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "client_id")
    private String clientId;

    //有效时长（单位：分钟）
    @Column(name = "expires")
    private long expires;
    //生成日期
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "refresh_token")
    private String refreshToken;

    public AccessToken(int userId,String clientId) {
        this.id= UUID.randomUUID().toString().replace("-","");
        this.clientId=clientId;
        this.userId=userId;
        this.createTime=new Date();
        this.expires = 60*8;//默认八小时
        this.token = TokenUtil.generateToken();
    }

    // 判断token是否过期
    public boolean isExpired()
    {
        if(this.expires <=0)
            return true;
        //判断当前Token是否有效
        long nowTime = new Date().getTime();
        long expiresTime = this.createTime.getTime() + (this.expires * 1000 * 60);
        return expiresTime <= nowTime;
    }
}
