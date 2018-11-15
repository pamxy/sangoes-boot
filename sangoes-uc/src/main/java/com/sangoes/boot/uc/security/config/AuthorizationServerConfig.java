package com.sangoes.boot.uc.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 3:41 PM
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private TokenStore redisTokenStore;


    /**
     * 配置客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("sangoes");
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("sangoes")
                .resourceIds("order")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret);

    }

    /**
     * 配置endpoints
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
                .tokenStore(redisTokenStore)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        //token增强配置
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);

        // 设置
        endpoints
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);

    }

    /**
     * 配置认证
     *
     * @param oauthServer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //允许表单认证
        oauthServer
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

}
