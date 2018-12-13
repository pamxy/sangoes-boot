package com.sangoes.boot.uc.security.config;

import com.sangoes.boot.common.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Copyright (c) 2018
 * jwt配置
 *
 * @author jerrychir
 * @date 2018/11/15 5:27 PM
 */
@Configuration
public class JwtTokenConfig {

    /**
     * 签名
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(SecurityConstants.JWT_SIGN_KEY);
        return accessTokenConverter;
    }

    /**
     * jwt 增强
     *
     * @return
     */
    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}
