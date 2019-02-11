package com.sangoes.boot.common.aop.log.annotation;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 日志注解 拦截
 *
 * @author jerrychir
 * @date 2018 2018/12/25 2:50 PM
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecLog {
    /**
     * 默认标题
     *
     * @return
     */
    String value() default "";
}
