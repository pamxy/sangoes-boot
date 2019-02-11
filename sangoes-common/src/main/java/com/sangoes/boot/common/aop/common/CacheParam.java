package com.sangoes.boot.common.aop.common;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 分布式锁参数
 *
 * @author jerrychir
 * @date 2019 2019/1/15 3:24 PM
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
