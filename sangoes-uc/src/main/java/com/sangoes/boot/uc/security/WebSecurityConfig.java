package com.sangoes.boot.uc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 11:00 AM
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
}
