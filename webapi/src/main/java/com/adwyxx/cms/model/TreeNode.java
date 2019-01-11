package com.adwyxx.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/1/11 16:55
 */
@Data
@AllArgsConstructor
@ToString
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -7167153591341559936L;

    private Integer id;
    private String label;
    private List<TreeNode> children;
    private boolean checked;

    public TreeNode()
    {
        this.checked=false;
        this.children=new ArrayList<>();
    }

}
