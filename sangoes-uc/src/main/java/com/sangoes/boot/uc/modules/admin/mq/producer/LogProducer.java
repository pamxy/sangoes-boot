package com.sangoes.boot.uc.modules.admin.mq.producer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.common.constants.SecurityConstants;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.uc.modules.admin.entity.SysLog;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/12 10:01 PM
 */
@Deprecated
@Component
public class LogProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 生产log 保存log到队列
     *
     * @param request
     * @param response
     */
    public void saveLogQueue(HttpServletRequest request, HttpServletResponse response) {
        //新建log
        SysLog log = new SysLog();
        // 获取request
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String remote = request.getRemoteHost();
        String params = HttpUtil.toParams(request.getParameterMap());
        // 获取response
        int status = response.getStatus();
        // authentication
        if (!StrUtil.equals(SecurityConstants.ANONYMOUS, AuthUtils.getPrincipal().toString())) {
            log.setAuthToken(AuthUtils.getToken());
        }
        // 设置
        log.setUrl(url);
        log.setMethod(method);
        log.setUri(uri);
        log.setRemote(remote);
        log.setParams(params);
        log.setStatus(status);
        // 消息队列 producer
        rabbitTemplate.convertAndSend(RabbitConstants.LOG_DIRECT_QUEUE, log);
    }
}
