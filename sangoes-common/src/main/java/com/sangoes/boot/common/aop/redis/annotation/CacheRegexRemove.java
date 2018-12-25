package com.sangoes.boot.common.aop.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/19 12:43 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface CacheRegexRemove {

    /**
     * 命名空间
     *
     * @return
     */
    public String[] values() default {};


    /**
     * 缓存keys
     *
     * @return
     */
    public String[] keys() default {};

}
