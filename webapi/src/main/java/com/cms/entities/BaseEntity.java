package com.cms.entities;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
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
