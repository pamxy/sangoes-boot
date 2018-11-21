package com.sangoes.boot.uc.security.config;

import com.sangoes.boot.uc.constants.SecurityConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018
 * jwt增强
 *
 * @author jerrychir
 * @date 2018/11/15 5:29 PM
 */

public class JwtTokenEnhancer implements TokenEnhancer {
    /**
     * 增强
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        info.put(SecurityConstants.JWT_ENHANCER_PROVIDER, SecurityConstants.JWT_ENHANCER_NAME);
        //设置附加信息
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
