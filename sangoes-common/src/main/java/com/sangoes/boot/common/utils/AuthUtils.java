package com.sangoes.boot.common.utils;

import com.sangoes.boot.common.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
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
     * 用户名
     *
     * @return
     */
    public static String getUserName() {
        Map<String, Object> userDetails = getUserDetails();
        return userDetails.get("username").toString();
    }

    /**
     * 获取角色
     *
     * @return List<String>
     */
    public static List<String> getListUserRoles() {
        Collection<? extends GrantedAuthority> authorities = getAuthentication().getAuthorities();
        return ArrayUtils.objectArrayToListString(authorities.toArray());
    }

    /**
     * 获取角色String
     *
     * @return String
     */
    public static String getUserRoles() {
        Collection<? extends GrantedAuthority> authorities = getAuthentication().getAuthorities();
        return StringUtils.join(authorities.toArray(), ",");
    }

    /**
     * 获取用户详细
     *
     * @return
     */
    public static Map<String, Object> getUserDetails() {
        try {
            return ObjectUtils.objectToMap(getPrincipal());
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

    /**
     * 获取Authentication
     *
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取Principal
     *
     * @return
     */
    public static Object getPrincipal() {
        return getAuthentication().getPrincipal();
    }
}
