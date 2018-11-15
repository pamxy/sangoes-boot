package com.sangoes.boot.uc.security.authention;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 * 登录成功处理器
 *
 * @author jerrychir
 * @date 2018/11/15 6:26 PM
 */
@Slf4j
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("xxxxxxxxxxxxxxxxx");
    }
}
