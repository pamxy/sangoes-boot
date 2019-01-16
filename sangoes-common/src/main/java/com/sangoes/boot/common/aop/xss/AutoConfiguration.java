package com.sangoes.boot.common.aop.xss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 自动配置
 *
 * @author jerrychir
 * @date 2018 2018/12/19 2:59 PM
 */
@ComponentScan({"com.sangoes.boot.common.aop.date"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
}
