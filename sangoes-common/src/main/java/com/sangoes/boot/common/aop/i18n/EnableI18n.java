package com.sangoes.boot.common.aop.i18n;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/19 2:58 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableI18n {
}
