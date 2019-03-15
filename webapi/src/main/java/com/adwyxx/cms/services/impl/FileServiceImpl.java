package com.adwyxx.cms.services.impl;

import com.adwyxx.cms.entities.Attachment;
import com.adwyxx.cms.model.ResponseModel;
import com.adwyxx.cms.model.ResponseStatus;
import com.adwyxx.cms.repositories.AttachmentRespository;
import com.adwyxx.cms.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/13 10:42
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private AttachmentRespository respository;

    @Override
    public Attachment findById(String id) {
        return respository.getOne(id);
    }

    @Override
    public void save(Attachment data) {
        respository.save(data);
    }

    /**
    * @description : 保存文件，上传文件到服务器目录，并保存文件信息到数据库
     * @param  file:文件
     * @param filePath :文件保存到服务器的路径
     * @param downloadUrl :下载链接域名地址
    * @author : Leo.W
    * @date : 2019/3/13 17:24
    * @return :
    **/
    private Attachment saveFile(MultipartFile file,String filePath,String downloadUrl) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("文件为空，无法上传。");
        }
        String fileName = file.getOriginalFilename();
        String[] names = fileName.split("[.]");
        // 获取后缀
        String suffixName = names[names.length - 1];
        // fileName处理
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String name =uuid + "." + suffixName;
        String path = filePath+name;
        // 文件对象
        File dest = new File(path);

        // 判断文件父目录是否存在,不存在就创建路径
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        //保存文件
        file.transferTo(dest);

        Attachment attachment = new Attachment();
        attachment.setId(uuid);
        attachment.setClassName("file");
        attachment.setFileName(fileName);
        attachment.setFilePath(path);
        attachment.setSuffixName(suffixName);
        attachment.setFileSize(file.getSize());
        attachment.setUrl(downloadUrl+name);
        //保存数据库
        this.save(attachment);
        return attachment;
    }

    /**
    * @description : 上传单个文件
     * @param  file:文件
     * @param filePath :文件保存到服务器的路径
     * @param downloadUrl :下载链接域名地址
    * @author : Leo.W
    * @date : 2019/3/13 17:23
    * @return :
    **/
    @Override
    public ResponseModel uplaodFile(MultipartFile file,String filePath,String downloadUrl) {
        ResponseModel model = new ResponseModel();
        //判断文件是否为空
        if (file.isEmpty()) {
            model.setMessage("上传文件为空");
            model.setStatus(ResponseStatus.SYSTEM_ERROR);
            return model;
        }

        try {
            //保存文件
            Attachment attachment = this.saveFile(file,filePath,downloadUrl);
            model.setMessage(attachment.getFileName()+"上传成功");
            model.setData(attachment);
        } catch (IOException e) {
            model.setStatus(ResponseStatus.SYSTEM_ERROR);
            model.setMessage(file.getOriginalFilename()+"上传失败："+e.getMessage());
        }
        return model;
    }

    /**
    * @description :多文件上传
    * @param  files:文件集合
     * @param filePath :文件保存到服务器的路径说
    * @param downloadUrl :下载链接域名地址
    * @author : Leo.W
    * @date : 2019/3/13 17:22
    * @return : 操作结果
    **/
    @Override
    public ResponseModel uplaodFiles(MultipartFile[] files, String filePath, String downloadUrl) {
        ResponseModel model = new ResponseModel();
        List<Attachment> list = new ArrayList<>();
        StringBuilder message = new StringBuilder();
        for(MultipartFile file:files)
        {
            try{
                Attachment attachment = this.saveFile(file,filePath,downloadUrl);
                list.add(attachment);
            }
            catch (IOException e)
            {
                message.append(e.getMessage());
            }
        }
        model.setData(list);
        if(message.length()>0)
        {
            model.setMessage(message.toString());
        }
        return model;
    }
}
