package com.sangoes.boot.uc.modules.admin.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/16 11:02 PM
 */
public interface PermissionService {
    /**
     * 判断请求是否有权限
     *
     * @param request        HttpServletRequest
     * @param authentication 认证信息
     * @return 是否有权限
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
