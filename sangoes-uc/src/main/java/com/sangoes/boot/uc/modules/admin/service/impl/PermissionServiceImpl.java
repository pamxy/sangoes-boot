package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.constants.SecurityConstants;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;
import com.sangoes.boot.uc.modules.admin.service.PermissionService;
import com.sangoes.boot.uc.modules.admin.vo.AuthVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (c) 2018
 * 是否有权限实现
 *
 * @author jerrychir
 * @date 2018/11/16 11:03 PM
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysAuthService authService;

    /**
     * 路径匹配规则
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断请求是否有权限
     *
     * @param request        HttpServletRequest
     * @param authentication 认证信息
     * @return 是否有权限
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        // 获取权限
        List<SimpleGrantedAuthority> authorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        // 线程安全原子类
        AtomicBoolean hasPermission = new AtomicBoolean(false);
        // 判断principal不为null
        if (ObjectUtil.isNotNull(principal)) {
            // 判断authorityList是否为空
            if (CollUtil.isEmpty(authorityList)) {
                return false;
            }
            // 权限action
            Set<AuthVo> actions = new HashSet<>();
            // 遍历角色
            authorityList.stream()
                    .filter(authority -> !StrUtil.equals(authority.getAuthority(), SecurityConstants.BASE_ROLE))
                    .forEach(authority -> {
                        // 查询权限
                        List<AuthVo> authVos = authService.listAuthByRoleCode(authority.getAuthority());
                        log.info("拥有权限:{}",authVos);
                        CollUtil.addAll(actions, authVos);
                    });
            // 遍历权限action
            actions.stream()
                    .filter(action -> StrUtil.isNotEmpty(action.getAction())
                            // 请求api地址
                            && antPathMatcher.match(action.getAction(), request.getRequestURI())
                            // 请求方法
                            && request.getMethod().equalsIgnoreCase(action.getMethod()))
                    .findFirst().ifPresent(menuVO -> hasPermission.set(true));

        }
        // principal为null 返回false
        return hasPermission.get();
    }
}
