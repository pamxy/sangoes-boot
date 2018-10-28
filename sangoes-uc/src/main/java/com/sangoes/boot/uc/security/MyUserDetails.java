package com.sangoes.boot.uc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 12:15 PM
 */
@Service
public class MyUserDetails implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
