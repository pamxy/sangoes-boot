package com.sangoes.boot.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/27 8:06 PM
 */
@SpringBootApplication
@MapperScan(basePackages = "tk.mybatis.springboot.mapper")
public class UCApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCApplication.class, args);


    }
}
