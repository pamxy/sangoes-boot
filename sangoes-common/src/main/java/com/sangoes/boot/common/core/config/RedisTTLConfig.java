package com.sangoes.boot.common.core.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * redis模块和时间配置
 *
 * @author jerrychir
 * @date 2018 2018/12/19 7:20 PM
 */
@Data
@Configuration
@ConditionalOnExpression("!'${redis.ttl}'.isEmpty()")
@ConfigurationProperties(prefix = "redis")
public class RedisTTLConfig {
    Map<String, String> ttl = new HashMap<>();
}
