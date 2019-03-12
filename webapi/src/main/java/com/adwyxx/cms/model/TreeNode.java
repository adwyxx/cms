package com.adwyxx.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    private int id;
    private String label;
    private String fullPath;
    private List<TreeNode> children;
    private boolean checked;

    public TreeNode()
    {
        this.checked=false;
        this.children=new ArrayList<>();
    }

    public TreeNode(int id,String label)
    {
        this.id=id;
        this.label=label;
        this.children=new ArrayList<>();
        this.checked=false;
    }

    public TreeNode(int id,String label,String fullPath)
    {
        this.id=id;
        this.label=label;
        this.fullPath=fullPath;
        this.children=new ArrayList<>();
        this.checked=false;
    }

    public TreeNode addChild(TreeNode child)
    {
        if(this.children==null)
        {
            this.children=new ArrayList<>();
        }
        this.children.add(child);

        return  this;
    }
}
