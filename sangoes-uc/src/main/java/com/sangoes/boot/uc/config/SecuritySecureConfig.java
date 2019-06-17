package com.sangoes.boot.uc.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/14 3:19 PM
 */
//@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
//    private final String adminContextPath;
//
//    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
//        this.adminContextPath = adminServerProperties.getContextPath();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//
//        http.authorizeRequests()
//                .antMatchers(adminContextPath + "/assets/**").permitAll()
//                .antMatchers(adminContextPath + "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
//                .logout().logoutUrl(adminContextPath + "/logout").and()
//                .httpBasic().and()
//                .csrf().disable();
//    }
}
