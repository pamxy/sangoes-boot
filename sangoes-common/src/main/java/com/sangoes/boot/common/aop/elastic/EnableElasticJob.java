package com.sangoes.boot.common.aop.elastic;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 开启定时服务
 *
 * @author jerrychir
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableElasticJob {
}
