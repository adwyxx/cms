package com.adwyxx.cms.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/13 09:16
 */
@Data
@Entity
@Table(name="attachment")
public class Attachment extends BaseEntity implements Serializable {

   private static final long serialVersionUID = 4018988460312386137L;

    @Id
    @Column(name="id",nullable = false)
    private String id;
    //类别名称
    @Column(name="class_name",nullable = false)
    private String className;
    //文件名称
    @Column(name="file_name",nullable = false)
    private String fileName;
    //文件保存路径
    @Column(name="file_path",nullable = false)
    private String filePath;
    //后缀名
    @Column(name="suffix_name",nullable = false)
    private String suffixName;
    //后缀名
    @Column(name="url",nullable = false)
    private String url;
    //文件大小 KB
    @Column(name="file_size",nullable = false)
    private long fileSize;

    public Attachment()
    {
     this.setCreateTime(new Date());
    }
}
