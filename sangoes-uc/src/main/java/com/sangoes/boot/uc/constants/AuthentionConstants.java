package com.sangoes.boot.uc.constants;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 5:20 PM
 */
public interface AuthentionConstants {

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
    String JWT_SIGN_KEY= "sangoes";
}
