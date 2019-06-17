package com.sangoes.boot.uc;

import com.sangoes.boot.common.core.componet.AliyunOSSUploader;
import com.sangoes.boot.common.core.config.AliyunOSSConfig;
import com.sangoes.boot.uc.modules.admin.service.IFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/19 11:42 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OSSTest {

    @Autowired
    private AliyunOSSUploader aliyunOSSUploader;

    @Autowired
    private IFileService fileService;

    @Test
    public void testUpload(){
        String upload = aliyunOSSUploader.upload(new File("u.png"));
        System.out.println("aliyunOSSConfig:"+upload);
    }

    @Test
    public void uploadWord(){
        File file = new File("/Users/jerrychir/Desktop/test.docx");



        MultipartFile multi = null;
        try {
            FileInputStream in_file = new FileInputStream(file);
            // 转 MultipartFile
            multi = new MockMultipartFile("模板.xls", in_file);
        } catch (Exception e) {
            e.printStackTrace();
        }



        fileService.uploadFile(multi);

    }
}
