package com.sangoes.boot.uc.security;

import com.sangoes.boot.uc.config.IgnoreUrlsConfig;
import com.sangoes.boot.uc.security.filter.AuthenticationsFilter;
import com.sangoes.boot.uc.security.handler.AuthSuccessHandler;
import com.sangoes.boot.uc.security.handler.AuthenticationFailHandler;
import com.sangoes.boot.uc.security.provider.AuthenticationsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 3:54 PM
 */
@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;


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
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin().loginPage("/signin").permitAll()
                        .and()
                        .authorizeRequests();

        ignoreUrlsConfig.getApis().forEach(api -> registry.antMatchers(api).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring().and().ignoring();
        ignoreUrlsConfig.getUrls().forEach(ignoring::antMatchers);
    }

    /**
     * 自定义登录类
     *
     * @return
     */
    @Bean
    public AuthenticationsFilter authenticationsFilter() {
        // 创建对象
        AuthenticationsFilter filter = new AuthenticationsFilter();
        // 设置授权
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 登录成功回调
        AuthSuccessHandler authSuccessHandler = new AuthSuccessHandler();
        authSuccessHandler.setClientDetailsService(clientDetailsService);
        authSuccessHandler.setPasswordEncoder(passwordEncoder());
        authSuccessHandler.setAuthorizationServerTokenServices(authorizationServerTokenServices);
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        // 登录失败回调
        filter.setAuthenticationFailureHandler(new AuthenticationFailHandler());
        return filter;
    }

    /**
     * provider
     *
     * @return
     */
    @Bean
    public AuthenticationsProvider authenticationsProvider() {
        AuthenticationsProvider provider = new AuthenticationsProvider();
        return provider;
    }



}
