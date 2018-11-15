package com.sangoes.boot.uc.constants;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/5 2:36 PM
 */
public interface SecurityConstants {
    /**
     * Authorization
     */
    String SECURITY_AUTHORIZATION = "Authorization";
    /**
     * Basic
     */
    String SECURITY_BASIC = "Basic ";
    /**
     * Bearer
     */
    String SECURITY_BEARER = "Bearer ";

    /**
     * 认证redis前缀
     */
    String OAUTH_REDIS_PREFIX = "sangoes:";

    /**
     * jwt增强
     */
    String JWT_ENHANCER_PROVIDER = "provider";

    /**
     * jwt增强名字
     */
    String JWT_ENHANCER_NAME = "jerrychir";

    /**
     * jwt signkeys
     */
    String JWT_SIGN_KEY = "sangoes";

    /**
     * 基本角色
     */
    String BASE_ROLE = "user";

    /**
     * 用户启用状态
     */
    String USER_STATUS_NORMAL = "300";
    /**
     * 用户禁用状态
     */
    String USER_STATUS_LOCKED = "301";
}
