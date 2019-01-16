package com.sangoes.boot.common.aop.ratelimit.aspect;

import com.sangoes.boot.common.aop.common.key.CacheKeyGenerator;
import com.sangoes.boot.common.aop.ratelimit.annotation.RateLimiter;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/25 2:52 PM
 */
@Slf4j
@Component
@Scope
@Aspect
public class RateLimiterAspect {

    @Autowired
    private RateLimiterHelper rateLimiterHelper;
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(com.sangoes.boot.common.aop.ratelimit.annotation.RateLimiter)")
    public Object interceptor(ProceedingJoinPoint pjp) throws IOException {
        HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RateLimiter limitAnnotation = method.getAnnotation(RateLimiter.class);
        final String prefix = limitAnnotation.prefix();
        final String delimiter = limitAnnotation.delimiter();
        final String description = limitAnnotation.description();
        final long count = limitAnnotation.count();
        final long limitExpire = limitAnnotation.expire();
        final long seconds = Expiration.from(limitExpire, limitAnnotation.timeUnit()).getExpirationTimeInSeconds();
        String key = cacheKeyGenerator.generate(prefix, delimiter, pjp);
        try {
            final boolean acquire = this.rateLimiterHelper.tryAcquire(key, count, seconds, description);
            if (acquire) {
                return pjp.proceed();
            } else {
                throw new RuntimeException(limitAnnotation.message());
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                Result.noReturn(e.getLocalizedMessage(), HttpStatus.TOO_MANY_REQUESTS, response);
                throw new RuntimeException(e.getLocalizedMessage());
            }
            Result.noReturn(e.getLocalizedMessage(), HttpStatus.TOO_MANY_REQUESTS, response);
            throw new RuntimeException("server exception");
        }
    }

}
