package com.sangoes.boot.common.aop.ratelimit.annotation;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/25 2:50 PM
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 每秒限制最多多少个请求 permits=-1表示不限流
     *
     * @return
     */
    public int permits() default -1;
}
