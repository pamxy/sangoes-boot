package com.sangoes.boot.uc;

import com.sangoes.boot.common.core.componet.AliyunOSSUploader;
import com.sangoes.boot.common.core.config.AliyunOSSConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

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
    @Test
    public void testUpload(){
        String upload = aliyunOSSUploader.upload(new File("u.png"));
        System.out.println("aliyunOSSConfig:"+upload);
    }
}
