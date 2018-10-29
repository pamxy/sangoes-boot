package com.sangoes.boot.uc;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/27 8:06 PM
 */
@SpringBootApplication
@EnableSwagger2Doc
//@ComponentScan(basePackages = {"com.sangoes.boot.common.core.config"})
@MapperScan(basePackages = "com.sangoes.boot.uc.modules.admin.mapper")
public class UCApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCApplication.class, args);


    }
}
