package com.sangoes.boot.common.aop.ratelimit.aspect;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/25 2:52 PM
 */

@Component
@Scope
@Aspect
public class RateLimiterAspect {

    /**
     * 一秒5个请求
     */
    private RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.sangoes.boot.common.aop.ratelimit.annotation.RateLimiter)")
    public void pointcut() {
    }

    /**
     * 实现
     *
     * @param joinPoint
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 拦截
        boolean flag = rateLimiter.tryAcquire();
        return rateLimiter;
    }
}
