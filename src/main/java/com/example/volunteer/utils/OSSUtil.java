package com.example.volunteer.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;

@Component
public class OSSUtil {
    private static final Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    //填写自己的帐号信息
    private static final String endpoint = "oss-cn-shanghai-internal.aliyuncs.com";
    private static final String accessKeyId = "LTAI4G1bxMXZJfUcEzGcBzeC";
    private static final String accessKeySecret = "BANCWn07sQDUqpYatmuKCzIleEcslM";

    public void createBucket(String bucketName, CannedAccessControlList acl) {

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if(ossClient.doesBucketExist(bucketName)){
            // 关闭OSSClient。
            ossClient.shutdown();
            System.out.println("exists");
            return;
        }

        /* 通过一个Bucket对象来创建 */
        CreateBucketRequest bucketObj = new CreateBucketRequest(null);// 构造函数入参为Bucket名称，可以为空
        bucketObj.setBucketName(bucketName);// 设置bucketObj名称
        bucketObj.setCannedACL(acl);// 设置bucketObj访问权限acl
        ossClient.createBucket(bucketObj);// 创建Bucket

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    // 上传文件
    public String uploadFile(String bucketName, MultipartFile fileupload) throws OSSException, ClientException{

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if(!ossClient.doesBucketExist(bucketName)){
            // 关闭OSSClient。
            ossClient.shutdown();
            logger.error("[uploadFile Ex], bucketName: {} doesn't exist", SerialUtil.toJsonStr(bucketName));
            return null;
        }

        String objectName = fileupload.getName();
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(fileupload.getBytes()));
        }catch (Exception e) {
            //上传失败
            logger.error("[uploadFile Ex], fileupload: {}", SerialUtil.toJsonStr(fileupload.getName()), e);
        }

        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        URL url=ossClient.generatePresignedUrl(bucketName,objectName,expiration);

        // 关闭OSSClient。
        ossClient.shutdown();

        if(url != null)
            return url.toString();
        else
            return null;
    }

    // 下载文件
    public Boolean downloadFile(String bucketName, String filename, String destinationPath) throws OSSException, ClientException{
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if(!ossClient.doesBucketExist(bucketName)){
            // 关闭OSSClient。
            ossClient.shutdown();
            logger.error("[uploadFile Ex], bucketName: {} doesn't exist", SerialUtil.toJsonStr(bucketName));
            return false;
        }

        ObjectMetadata objectMetadata = ossClient.getObject(new GetObjectRequest(bucketName, filename), new File(destinationPath+filename));

        // 关闭OSSClient。
        ossClient.shutdown();

        if(objectMetadata != null)
        return true;
        else {
            logger.error("[downloadFile Ex], filename: {}, destinationPath: {}", SerialUtil.toJsonStr(filename),SerialUtil.toJsonStr(destinationPath));
            return false;
        }
    }

    public Boolean deleteFile(String bucketName, String filename){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        if(!ossClient.doesBucketExist(bucketName)){
            // 关闭OSSClient。
            ossClient.shutdown();
            logger.error("[uploadFile Ex], bucketName: {} doesn't exist", SerialUtil.toJsonStr(bucketName));
            return false;
        }

        // 删除文件。
        ossClient.deleteObject(bucketName, filename);

        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

}
