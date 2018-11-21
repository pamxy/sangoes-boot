package com.sangoes.boot.uc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 * 验证码验证过滤器
 *
 * @author jerrychir
 * @date 2018/11/19 5:25 PM
 */
@Slf4j
@Component
public class ValidationCodeFilter extends OncePerRequestFilter {

    /**
     * 过滤
     * filterChain.doFilter(request, response) 必须在最前面 不然会报 java.lang.IllegalStateException: getWriter() has already been called for this response
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 放行
        filterChain.doFilter(request, response);
    }
}
