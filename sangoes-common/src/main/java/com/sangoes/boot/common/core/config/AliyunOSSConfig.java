package com.sangoes.boot.common.core.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2018
 * 阿里云oss配置
 *
 * @author jerrychir
 * @date 2018/11/19 12:16 PM
 */
@Configuration
@Data
@ConditionalOnExpression("!'${sangoes.aliyun}'.isEmpty()")
@ConfigurationProperties(prefix = "sangoes.aliyun.oss")
public class AliyunOSSConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String key;

    private String fileHost;

    private String host;
}
