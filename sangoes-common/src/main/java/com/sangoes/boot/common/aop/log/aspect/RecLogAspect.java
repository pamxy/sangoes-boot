package com.sangoes.boot.common.aop.log.aspect;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.common.constants.SecurityConstants;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class RecLogAspect {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Controller 拦截点,前置通知
     */
    @Pointcut("@annotation(com.sangoes.boot.common.aop.log.annotation.RecLog)")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     */
    @Around("logPointCut()")
    public Object doPoint(ProceedingJoinPoint point) throws Throwable {
        // 开始时间
        long start = SystemClock.now();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long elapsed = SystemClock.now() - start;
        // 保存日志
        saveSysLog(point, elapsed);
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param elapsed
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long elapsed) {
        // MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // method
        Method method = signature.getMethod();
        // 获取主键参数
        RecLog recLog = method.getAnnotation(RecLog.class);
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        String params = null;
        if (args.length > 0) {
            params = JSONUtil.toJsonStr(args[0]);
        }
        // 获取
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String remote = request.getRemoteHost();
        // 获取response
        int status = response.getStatus();
        // 创建SysLOg
        RecLogModel recLogModel = new RecLogModel();
        // authentication
        if (!StrUtil.equals(SecurityConstants.ANONYMOUS, AuthUtils.getPrincipal().toString())) {
            recLogModel.setCreator(AuthUtils.getUserName());
            recLogModel.setCreatorId(AuthUtils.getUserId());
            recLogModel.setAuthToken(AuthUtils.getToken());
        }
        if (ObjectUtil.isNotNull(recLog)) {
            // 注解描述
            recLogModel.setTitle(recLog.value());
        }
        recLogModel.setUrl(url);
        recLogModel.setMethod(methodName);
        recLogModel.setUri(uri);
        recLogModel.setRemote(remote);
        recLogModel.setParams(params);
        recLogModel.setStatus(status);
        recLogModel.setElapsed(String.valueOf(elapsed));
        // 放入队列
        rabbitTemplate.convertAndSend(RabbitConstants.LOG_DIRECT_QUEUE, JSONUtil.toJsonStr(recLogModel));
    }

    /**
     * TODO 操作异常记录
     *
     * @param point 切点
     * @param e     异常
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        log.error("erro:{}", e);
    }
}

