package com.sangoes.boot.uc.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/29 1:31 PM
 */
@Data
@Configuration
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class IgnoreUrlsConfig {
    private List<String> apis = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

}
