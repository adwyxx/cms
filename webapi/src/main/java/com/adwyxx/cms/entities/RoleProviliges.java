package com.adwyxx.cms.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 17:24
 */
@Data
@Entity
@Table(name = " role_proviliges")
@Proxy(lazy = false)
@NoArgsConstructor
public class RoleProviliges extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2022180383915128163L;
    private Integer roleId;
    private Integer menuId;

    public RoleProviliges(Integer roleId, Integer menuId, String creator)
    {
        this.roleId=roleId;
        this.menuId=menuId;
        this.setCreator(creator);
        this.setCreateTime(new Date());
    }

}
