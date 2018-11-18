package com.sangoes.boot.uc.utils;

import com.sangoes.boot.uc.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Copyright (c) 2018
 * 授权工具类
 *
 * @author jerrychir
 * @date 2018/11/17 9:34 PM
 */
@Slf4j
public class AuthUtils {

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static Long getUserId() {
        Map<String, Object> userDetails = getUserDetails();
        return Long.parseLong(userDetails.get("userId").toString());
    }

    /**
     * 获取用户详细
     *
     * @return
     */
    public static Map<String, Object> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return ObjectUtils.objectToMap(authentication.getPrincipal());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取请求中token
     *
     * @param request request
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(SecurityConstants.SECURITY_AUTHORIZATION);
        return StringUtils.substringAfter(authorization, SecurityConstants.SECURITY_BEARER);
    }
}
