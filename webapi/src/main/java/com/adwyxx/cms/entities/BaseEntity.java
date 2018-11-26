package com.adwyxx.cms.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass //父类的属性子类可以继承
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 8641108338538325713L;
    //创建时间
    @Column(name="create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //创建人
    @Column(name="creator")
    private String creator;
}
