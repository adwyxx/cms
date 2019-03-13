package com.adwyxx.cms.controllers;

import com.adwyxx.cms.entities.Attachment;
import com.adwyxx.cms.model.ResponseModel;
import com.adwyxx.cms.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/13 09:13
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    // 文件上产的路径
    @Value("${cms.uploadFilePath}")
    private String filePath;
    @Value("${cms.downloadFileUrl}")
    private String downloadUrl;

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadByBody")
    public ResponseModel uploadByBody(@RequestBody MultipartFile file)
    {
        return fileService.uplaodFile(file,filePath,downloadUrl);
    }

    @PostMapping("/upload")
    public ResponseModel upload(@RequestParam("file") MultipartFile file)
    {
        return fileService.uplaodFile(file,filePath,downloadUrl);
    }

    @PostMapping("/uploadFiles")
    public ResponseModel uploadFiles(@RequestParam("files") MultipartFile[] files)
    {
        return fileService.uplaodFiles(files,filePath,downloadUrl);
    }

    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, @PathVariable("id") String id)
    {
        Attachment fileSource = fileService.findById(id);

        if (fileSource!=null && fileSource.getFilePath() != null) {
            //设置文件路径
            File file = new File(fileSource.getFilePath() , fileSource.getFileName());
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                //response.setContentType("multipart/form-data;charset=UTF-8");也可以明确的设置一下UTF-8，测试中不设置也可以。
                response.setHeader("Content-Disposition", "attachment;fileName="+ fileSource.getFileName());
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
