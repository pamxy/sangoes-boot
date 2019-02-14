package com.sangoes.boot.common.aop.date;

import com.sangoes.boot.common.aop.date.aspect.LocalDateSerializerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 解决JDK8日期
 *
 * @author jerrychir
 * @date 2018 2018/12/19 2:58 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LocalDateSerializerConfig.class)
@Documented
@Inherited
public @interface EnableDate {
}
