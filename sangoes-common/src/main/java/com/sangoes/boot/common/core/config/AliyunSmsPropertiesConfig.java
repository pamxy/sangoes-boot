package com.sangoes.boot.common.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/3/4 1:41 PM
 */
@Data
@Configuration
@ConditionalOnExpression("!'${sangoes.aliyun}'.isEmpty()")
@ConfigurationProperties(prefix = "sangoes.aliyun.sms")
public class AliyunSmsPropertiesConfig {

    /**
     * 地域ID
     */
    @Value("${sangoes.aliyun.sms.regionId:cn-hangzhou}")
    private String regionId;

    /**
     * RAM账号的AccessKey ID
     */
    private String accessKeyId;

    /**
     * RAM账号Access Key Secret
     */
    private String accessKeySecret;

    /**
     * 域
     */
    @Value("${sangoes.aliyun.sms.domain:dysmsapi.aliyuncs.com}")
    private String domain;


}
