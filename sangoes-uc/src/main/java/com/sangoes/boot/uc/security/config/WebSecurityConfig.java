package com.sangoes.boot.uc.security.config;

import com.sangoes.boot.uc.config.IgnoreUrlsConfig;
import com.sangoes.boot.uc.security.authention.SecurityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 3:54 PM
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProvider securityProvider;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;


    /**
     * 加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = http
                .authorizeRequests();

        // 允许访问
        ignoreUrlsConfig.getApis().forEach(api -> {
            authenticated.antMatchers(api).permitAll();
        });
        // 任何尚未匹配的URL只需要验证用户即可访问
         authenticated.anyRequest().anonymous();
        // 任何尚未匹配的URL只需要验证用户即可访问
//        authenticated.anyRequest().authenticated();

    }

    /**
     * @return the {@link AuthenticationManager} to use
     * @throws Exception
     */
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(securityProvider));
        return providerManager;
    }
}
