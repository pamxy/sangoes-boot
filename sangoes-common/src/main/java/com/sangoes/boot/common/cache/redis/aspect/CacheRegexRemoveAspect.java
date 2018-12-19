package com.sangoes.boot.common.cache.redis.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.cache.redis.annotation.CacheRegexRemove;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/19 12:47 PM
 */
@Slf4j
@Aspect
@Service
public class CacheRegexRemoveAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 截获标有@CacheRemove的方法
     */
    @Pointcut(value = "(execution(* *.*(..)) && @annotation(com.sangoes.boot.common.cache.redis.annotation.CacheRegexRemove))")
    private void pointcut() {
    }

    /**
     * 拦截方法
     *
     * @param pjp
     */
    @AfterReturning(value = "pointcut()")
    private void process(JoinPoint pjp) throws Throwable {

        //获取被代理的类
        Object target = pjp.getTarget();
        //获取切入方法的数据
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取切入方法
        Method method = signature.getMethod();
        //获得注解
        CacheRegexRemove cacheRegexRemove = method.getAnnotation(CacheRegexRemove.class);
        // 判断
        if (ObjectUtil.isNotNull(cacheRegexRemove)) {
            // 根据类删除
            String className = target.getClass().toString();
            if (StrUtil.isNotBlank(className)) {
                this.removeCache("*" + className + "*");
            }
            // 根据命名空间删除
            String[] values = cacheRegexRemove.values();
            for (String value : values) {
                this.removeCache("*" + value + "*");
            }
            // 根据keys
            String[] keys = cacheRegexRemove.keys();
            for (String key : keys) {
                this.removeCache("*" + key + "*");
            }
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    private void removeCache(String key) {
        if (StrUtil.isNotBlank(key)) {
            Set<String> keys = redisTemplate.keys(key);
            redisTemplate.delete(keys);
        }
    }

}
