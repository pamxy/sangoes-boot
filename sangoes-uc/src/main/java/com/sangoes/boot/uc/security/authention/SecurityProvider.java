package com.sangoes.boot.uc.security.authention;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 8:59 PM
 */
@Slf4j
@Component
public class SecurityProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 转换
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        // 查询userDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationToken.getName());
        if (ObjectUtil.isNull(userDetails)) {
            throw new UsernameNotFoundException("找不到user");
        }
        // TODO 前端密码RSA解密
        // 判断密码是否相同
        String forntPassword = authenticationToken.getCredentials().toString();
        String passwordDb = userDetails.getPassword();
        if (!passwordEncoder.matches(forntPassword, passwordDb)) {
            throw new BadCredentialsException("密码不正确");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, passwordDb, userDetails.getAuthorities());
    }

    /**
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
