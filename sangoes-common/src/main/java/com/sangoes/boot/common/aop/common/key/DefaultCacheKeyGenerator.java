package com.sangoes.boot.common.aop.common.key;

import cn.hutool.core.util.ObjectUtil;
import com.sangoes.boot.common.aop.common.CacheParam;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * key实现
 *
 * @author jerrychir
 * @date 2019 2019/1/15 3:27 PM
 */
public class DefaultCacheKeyGenerator implements CacheKeyGenerator {


    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param prefix    key前缀
     * @param delimiter 分隔符
     * @param pjp       PJP
     * @return 缓存KEY
     */
    @Override
    public String generate(String prefix, String delimiter, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //  默认解析方法里面带 RedisParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++) {
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (ObjectUtil.isNull(annotation)) {
                continue;
            }
            builder.append(delimiter).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (ObjectUtil.isNull(annotation)) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(delimiter).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return prefix + builder.toString();
    }
}
