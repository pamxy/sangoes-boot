package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 12:15 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取UserDetailsVo
        UserDetailsVo userDetailsVo = userService.selectUserDetailsByUsername(username);
        if (ObjectUtil.isNull(userDetailsVo)) {
            throw new HandleErrorException("user没有找到");
        }
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.withUsername(userDetailsVo.getUsername())
                .password(passwordEncoder.encode(userDetailsVo.getPassword())).authorities(userDetailsVo.getRoles())
                .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
    }
}
