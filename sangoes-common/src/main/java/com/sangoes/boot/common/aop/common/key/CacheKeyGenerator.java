package com.sangoes.boot.common.aop.common.key;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * key生成器
 *
 * @author jerrychir
 * @date 2019 2019/1/15 3:26 PM
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @param pjp       PJP
     * @return 缓存KEY
     */
    String generate(String prefix, String delimiter, ProceedingJoinPoint pjp);
}
