package com.sangoes.boot.uc.security.token;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Copyright (c) 2018
 * 自定义token
 *
 * @author jerrychir
 * @date 2018/11/20 1:42 PM
 */
@Slf4j
public class AuthenticationsToken extends AbstractAuthenticationToken {
    /**
     * 用户名
     */
    private final Object principal;
    /**
     * credentials
     */
    private Object credentials;
    /**
     * 登录类型
     * type: mobile 手机登录 account 用户登录
     */
    @Setter
    @Getter
    private String type;

    /**
     * 手机号码
     */
    @Setter
    @Getter
    private String mobile;

    /**
     * 登录类型
     */
    @Setter
    @Getter
    private String signinType;

    /**
     * 秘钥、redis随机数
     */
    @Setter
    @Getter
    private String publicRandom;

    /**
     * 带authorities构造函数
     *
     * @param principal
     * @param credentials
     * @param type
     * @param mobile
     */
    public AuthenticationsToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials, String type, String mobile, String signinType, String publicRandom) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        this.mobile = mobile;
        super.setAuthenticated(false);
    }

    /**
     * 不带authorities构造函数
     *
     * @param principal   principal
     * @param credentials credentials
     * @param type        type
     * @param mobile      mobile
     */
    public AuthenticationsToken(Object principal, Object credentials, String type, String mobile, String signinType, String publicRandom) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        this.mobile = mobile;
        this.signinType = signinType;
        this.publicRandom = publicRandom;
        super.setAuthenticated(false);
    }

    /**
     */
    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    /**
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * 设置权限
     *
     * @param authenticated
     */
    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    /**
     * 移除Credentials
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
