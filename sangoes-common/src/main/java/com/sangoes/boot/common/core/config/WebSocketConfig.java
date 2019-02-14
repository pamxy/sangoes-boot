package com.sangoes.boot.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * web socket 配置
 *
 * @author jerrychir
 * @date 2019 2019/1/31 10:23 AM
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
