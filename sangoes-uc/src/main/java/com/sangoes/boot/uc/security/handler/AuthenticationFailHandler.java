package com.sangoes.boot.uc.security.handler;

import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.msg.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 * 登录失败处理器
 *
 * @author jerrychir
 * @date 2018/11/15 6:25 PM
 */
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    /**
     * 登录失败
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败:{}", exception);
        String msg = exception.getMessage();
        // 当为空时
        if (StrUtil.isBlank(msg)) {
            Result.noReturn("登录失败", HttpStatus.EXPECTATION_FAILED, response);
            return;
        }
        Result.noReturn(msg, HttpStatus.EXPECTATION_FAILED, response);
    }
}
