package com.feidian.util;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Component
public class QiniuKodoUtil {
    private final static String accessKey = "vY0733l99q3wJrJZoaAjsLAAqEN7l_yqhM14G5VA";
    private final static String secretKey = "U90owYwbndFXN1wQRszdWtBcuID7mUNWnDIC3fzg";
    private final static String bucket = "videodeliveryweb3reposity";
    private final static String domain = "http://rxs08ypx9.bkt.clouddn.com/";

    /**
     * 构造一个带指定 Region 对象的配置类，因为我的是华东机房，所以为Region.region0()
     */
    private final static Configuration cfg = new Configuration(Region.region0());
    static UploadManager uploadManager = new UploadManager(cfg);

    /**
     * 客户端上传文件到OSS的方法
     */
    public static String uploadFile(MultipartFile file, String uploadDir) {
        // 生成保存文件的路径，利用UUID生成唯一标识和原始文件名
        String filePath = uploadDir + UUID.randomUUID() + file.getOriginalFilename();

        // 上传到七牛云存储
        String key = filePath.substring(filePath.lastIndexOf("/") + 1);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
            return "文件上传失败";
        }
    }


    /**
     * 获取下载文件的链接
     *
     * @param fileName 文件名称
     * @return 下载文件的链接
     */
    public String getFileUrl(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String finalUrl = String.format("%s/%s", "http://" + domain, encodedFileName);
        System.out.println(finalUrl);
        return finalUrl;
    }

    /**
     * 批量删除空间中的文件
     *
     * @param fileList 文件名称列表
     */
    public void deleteFile(String[] fileList) {
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, fileList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < fileList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = fileList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }
}
