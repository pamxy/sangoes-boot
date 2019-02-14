package com.sangoes.boot.common.aop.lock.aspect;

import com.sangoes.boot.common.aop.common.key.CacheKeyGenerator;
import com.sangoes.boot.common.aop.lock.annotation.CacheLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/16 6:07 PM
 */
@Slf4j
@Component
@Scope
@Aspect
public class CacheLockAspect {

    @Autowired
    private CacheLockHelper redisLockHelper;

    @Autowired
    private CacheKeyGenerator redisKeyGenerator;


    @Around("execution(public * *(..)) && @annotation(com.sangoes.boot.common.aop.lock.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key prefix don't null...");
        }
        final String lockKey = redisKeyGenerator.generate(lock.prefix(), lock.delimiter(), pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
            if (log.isDebugEnabled()) {
                log.debug("Redis lock key is [{}] and status is [{}]", lockKey, success);
            }
            if (!success) {
                throw new RuntimeException(lock.message());
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("server exception");
            }
        } finally {
            redisLockHelper.unlock(lockKey, value);
        }
    }
}
