package com.sangoes.boot.uc.security.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.constants.SecurityConstants;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.AuthorizationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * clientDetailsService
     */
    private ClientDetailsService clientDetailsService;

    /**
     * passwordEncoder
     */
    private PasswordEncoder passwordEncoder;

    /**
     * authorizationServerTokenServices
     */
    private AuthorizationServerTokenServices authorizationServerTokenServices;


    /**
     * 授权成功
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("成功:{}", authentication);
        // 获取type
        String type = request.getParameter(SecurityConstants.TYPE);
        // 获取clients
        String[] clients = AuthorizationUtil.extractAndDecodeHeader(request);
        assert clients.length == 2;
        // 获取clientId
        String clientId = clients[0];
        // 获取secret
        String secret = clients[1];
        log.error("clientId:{}", clientId);
        // 获取clientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 校验secret
        log.error("clientDetails:{}", clientDetails);
        log.error("type:{}", request.getParameter("type"));
//        if (!passwordEncoder.matches(secret, clientDetails.getClientSecret())) {
//            Result.noReturn("client错误", HttpStatus.EXPECTATION_FAILED, response);
//            return;
//        }

        if (!StrUtil.equals(secret, clientDetails.getClientSecret())) {
            Result.noReturn("client错误", HttpStatus.EXPECTATION_FAILED, response);
            return;
        }
        // 获取tokenRequest
        TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(), type);
        //校验scope
        new DefaultOAuth2RequestValidator().validateScope(tokenRequest, clientDetails);
        // oAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        // oAuth2Authentication
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        // oAuth2AccessToken
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        log.info("获取token 成功：{}", oAuth2AccessToken);
        // 返回token
        Result.success(oAuth2AccessToken, "登录成功", response);
    }

    /**
     * set ClientDetailsService
     *
     * @param clientDetailsService
     */
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    /**
     * set PasswordEncoder
     *
     * @param passwordEncoder
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * set AuthorizationServerTokenServices
     *
     * @param authorizationServerTokenServices
     */
    public void setAuthorizationServerTokenServices(AuthorizationServerTokenServices authorizationServerTokenServices) {
        this.authorizationServerTokenServices = authorizationServerTokenServices;
    }
}
