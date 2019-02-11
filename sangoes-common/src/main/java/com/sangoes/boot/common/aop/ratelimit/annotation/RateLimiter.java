package com.sangoes.boot.common.aop.ratelimit.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 分布式限流注解
 *
 * @author jerrychir
 * @date 2018 2018/12/25 2:50 PM
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RateLimiter {

    /**
     * Key的prefix
     *
     * @return String
     */
    String prefix() default "";


    /**
     * 超时时间单位
     *
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * <p>Key的分隔符（默认 :）</p>
     * <p>生成的Key：N:SO1008:500</p>
     *
     * @return String
     */
    String delimiter() default ":";

    /**
     * 过期时间
     *
     * @return int
     */
    int expire() default 5;

    /**
     * 最多的访问限制次数
     *
     * @return int
     */
    long count() default 5;


    /**
     * 默认异常信息
     * "You have been dragged into the blacklist"
     *
     * @return String
     */
    String message() default "请求过多,请稍后再试";

    /**
     * 限流描述
     *
     * @return String
     */
    String description() default "";

}
