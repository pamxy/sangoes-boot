package com.sangoes.boot.uc.security.config;

import com.sangoes.boot.uc.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 5:37 PM
 */
@Configuration
public class TokenStoreConfig {
    /**
     * redis 连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置 redisTokenStore
     * @return
     */
    @Bean
    public TokenStore redisTokenStore(){
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        // 前缀 sangoes:
        tokenStore.setPrefix(SecurityConstants.OAUTH_REDIS_PREFIX);
        return tokenStore;
    }
}
