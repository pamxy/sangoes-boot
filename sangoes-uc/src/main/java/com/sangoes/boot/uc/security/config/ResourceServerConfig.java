package com.sangoes.boot.uc.security.config;

import com.sangoes.boot.uc.security.authention.AuthenticationFailHandler;
import com.sangoes.boot.uc.security.authention.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 3:40 PM
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private AuthenticationFailHandler authenticationFailHandler;
//
//    @Autowired
//    private AuthSuccessHandler authSuccessHandler;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("order").stateless(true);
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .failureForwardUrl("/authentication/form");
//                .successHandler(authSuccessHandler)
//                .failureHandler(authenticationFailHandler);
        http.authorizeRequests()
                //配置order访问控制，必须认证过后才可以访问
                .antMatchers("/order/**").authenticated();

    }
}
