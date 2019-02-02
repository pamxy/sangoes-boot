package com.sangoes.boot.common.core.aop;

import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.constants.SecurityConstants;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Copyright (c) 2018
 * controller 增强
 *
 * @author jerrychir
 * @date 2018/11/27 11:19 PM
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {
    /**
     * 切面
     */
    @Pointcut("execution(public com.sangoes.boot.common.msg.Result *(..))")
    public void pointCutResult() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有返回对象R
     * @return 结果包装
     */
    @Around("pointCutResult()")
    public Object methodRHandler(ProceedingJoinPoint pjp) throws Throwable {
        return methodHandler(pjp);
    }

    /**
     * 处理方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    private Object methodHandler(ProceedingJoinPoint pjp) throws Throwable {
        // 获取时间
        long startTime = System.currentTimeMillis();
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();

        // 获取username
        String username = request.getHeader(SecurityConstants.X_USER_HEADER);
        if (StrUtil.isNotBlank(username)) {
            // 记录下
            AuthUtils.setUser();
            log.info("Controller AOP get username:{}", username);
        }

        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed();
        log.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));


        return result;
    }
}
