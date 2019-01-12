package com.sangoes.boot.uc.filter;

import com.sangoes.boot.uc.modules.admin.mq.producer.LogProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 日志过滤
 *
 * @author jerrychir
 * @date 2019 2019/1/10 8:21 PM
 */

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {


    @Autowired
    private LogProducer logProducer;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 保存日志
        logProducer.saveLogQueue(request, response);
        // 放行
        filterChain.doFilter(request, response);
    }
}
