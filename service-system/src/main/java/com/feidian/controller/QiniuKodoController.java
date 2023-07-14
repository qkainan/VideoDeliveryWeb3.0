package com.feidian.controller;

import com.feidian.util.QiniuKodoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/qiniukodo")
public class QiniuKodoController {

    @Resource
    QiniuKodoUtil qiniuKodoUtil;


    /*@RequestMapping("/upload")
    public String upload(String localFilePath) {
        return qiniuKodoUtil.uploadFile(localFilePath);
    }*/


    
    @RequestMapping("/getFileUrl")
    public String getFileUrl(String fileName) {
        try {
            qiniuKodoUtil.getFileUrl(fileName);
            return "This is a test.";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "This is a test.";
    }

    @RequestMapping("/deleteFile")
    public void deleteFile(String[] fileList) {
        qiniuKodoUtil.deleteFile(fileList);
    }
}
