package com.sangoes.boot.uc.modules.admin.model.pojo;

import org.springframework.security.core.GrantedAuthority;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 12:08 PM
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}

