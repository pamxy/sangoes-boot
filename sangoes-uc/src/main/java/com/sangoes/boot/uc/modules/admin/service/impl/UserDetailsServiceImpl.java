package com.sangoes.boot.uc.modules.admin.service.impl;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 12:15 PM
 */
//@Service
public class UserDetailsServiceImpl {

//    @Autowired
//    private ISysUserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 获取UserDetailsVo
//        UserDetailsVo userDetailsVo = userService.selectUserDetailsByUsername(username);
//        if (ObjectUtil.isNull(userDetailsVo)) {
//            throw new HandleErrorException("user没有找到");
//        }
//        // 密码加密
//        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        // return User.withUsername(userDetailsVo.getUsername())
//        // .password(passwordEncoder.encode(userDetailsVo.getPassword())).authorities(userDetailsVo.getRoles())
//        // .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
//
//        return User.withUsername("demoUser1").password("123456").build();
//    }
}
