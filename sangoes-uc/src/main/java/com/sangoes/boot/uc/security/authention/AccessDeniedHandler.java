package com.sangoes.boot.uc.security.authention;

import com.sangoes.boot.common.msg.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 * 授权拒绝处理
 *
 * @author jerrychir
 * @date 2018/11/16 4:39 PM
 */
@Slf4j
@Component
public class AccessDeniedHandler extends OAuth2AccessDeniedHandler {

    /**
     * 授权拒绝处理
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        Result.noReturn("授权失败 禁止访问", HttpStatus.FORBIDDEN, response);
    }
}
