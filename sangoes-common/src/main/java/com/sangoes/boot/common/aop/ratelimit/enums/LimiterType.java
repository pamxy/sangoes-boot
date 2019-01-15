package com.sangoes.boot.common.aop.ratelimit.enums;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 限流类型
 *
 * @author jerrychir
 * @date 2019 2019/1/15 3:45 PM
 */
public enum LimiterType {

    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 根据请求者IP
     */
    IP;
}
