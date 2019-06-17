package com.sangoes.boot.common.core.componet;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.sangoes.boot.common.core.config.AliyunOSSConfig;
import com.sangoes.boot.common.exception.HandleErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Copyright (c) 2018
 * 阿里云上传
 *
 * @author jerrychir
 * @date 2018/11/19 11:28 AM
 */
@Slf4j
@Component
public class AliyunOSSUploader {

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    /**
     * 上传MultipartFile
     *
     * @param multipartFile
     * @return
     */
    public String uploadMultipartFile(MultipartFile multipartFile) {
        // 获取扩展名
        String fileExt = FileUtil.extName(multipartFile.getOriginalFilename());
        try {
            // 创建file
            File tempFile = File.createTempFile(System.currentTimeMillis() + "", "." + fileExt);
            // 转换
            multipartFile.transferTo(tempFile);
            // 上传
            String file = this.upload(tempFile);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String upload(File file) {
        log.info("oss file:{}", file);
        // 验证file
        if (ObjectUtil.isNull(file)) {
            throw new HandleErrorException("file为空");
        }
        // 配置项
        String endpoint = aliyunOSSConfig.getEndpoint();
        String accessKeyId = aliyunOSSConfig.getAccessKeyId();
        String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
        String bucketName = aliyunOSSConfig.getBucketName();
        String fileHost = aliyunOSSConfig.getFileHost();
        String host = aliyunOSSConfig.getHost();
        // oss client
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建新的bucketName
            if (!ossClient.doesBucketExist(bucketName)) {
                log.info("Creating bucket:{}",bucketName);
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 创建时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(new Date());
            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            // 添加host
            fileUrl = host + fileUrl;
            if (null != result) {
                log.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getMessage());
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
}
