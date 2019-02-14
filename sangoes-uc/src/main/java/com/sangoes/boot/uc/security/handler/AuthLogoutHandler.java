package com.sangoes.boot.uc.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/2/2 9:32 PM
 */
public class AuthLogoutHandler extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String token = request.getHeader(HEADER_AUTHORIZATION);
//
//        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
//
//            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[0]);
//
//            if (oAuth2AccessToken != null) {
//                tokenStore.removeAccessToken(oAuth2AccessToken);
//            }
//
//        }
//
//        response.setStatus(HttpServletResponse.SC_OK);
    }
}
