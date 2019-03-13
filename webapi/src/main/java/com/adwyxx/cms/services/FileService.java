package com.adwyxx.cms.services;

import com.adwyxx.cms.entities.Attachment;
import com.adwyxx.cms.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/13 10:36
 */
public interface FileService {
    Attachment findById(String id);
    void save(Attachment data);
    ResponseModel uplaodFile(MultipartFile file,String filePath,String downloadUrl);
    ResponseModel uplaodFiles(MultipartFile[] files,String filePath,String downloadUrl);
}
