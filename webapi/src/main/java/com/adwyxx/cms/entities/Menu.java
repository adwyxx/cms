package com.adwyxx.cms.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "menus")
@Proxy(lazy = false)
@NoArgsConstructor
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5076402248421521224L;
    private Integer id;
    private String name;
    private String url;
}
