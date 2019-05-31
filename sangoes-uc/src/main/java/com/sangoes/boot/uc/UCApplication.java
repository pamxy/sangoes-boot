package com.sangoes.boot.uc;

import com.sangoes.boot.common.aop.elastic.EnableElasticJob;
import com.sangoes.boot.common.aop.lock.EnableLock;
import com.sangoes.boot.common.aop.log.EnableLog;
import com.sangoes.boot.common.aop.ratelimit.EnableLimiter;
import com.sangoes.boot.common.aop.redis.EnableCache;
import com.spring4all.swagger.EnableSwagger2Doc;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
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
@EnableElasticJob
@EnableAsync
@SpringBootApplication
@EnableSwagger2Doc
@EnableCaching
@EnableCache
@EnableLog
@EnableLimiter
@EnableLock
//@EnableCrypto
@EnableRabbit
@EnableAdminServer
@ComponentScan(basePackages = {"com.sangoes.boot.uc", "com.sangoes.boot.common.core"})
public class UCApplication {

    public static void main(String[] args) {
        SpringApplication.run(UCApplication.class, args);
    }
}
