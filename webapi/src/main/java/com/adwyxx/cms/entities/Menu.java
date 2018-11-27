package com.adwyxx.cms.entities;

import lombok.Data;

@Data
public class Menu extends BaseEntity {
    private Integer id;
    private String name;
    private String url;

}
