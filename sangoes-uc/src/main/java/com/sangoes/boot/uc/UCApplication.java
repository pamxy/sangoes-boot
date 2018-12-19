package com.sangoes.boot.uc;

import com.sangoes.boot.common.cache.redis.EnableCache;
import com.sangoes.boot.common.core.config.RedisTTLConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/27 8:06 PM
 */
@EnableAsync
@SpringBootApplication
@EnableSwagger2Doc
@EnableCaching
@EnableCache
@ComponentScan(basePackages = {"com.sangoes.boot.uc", "com.sangoes.boot.common.core"})
public class UCApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCApplication.class, args);
    }
}
