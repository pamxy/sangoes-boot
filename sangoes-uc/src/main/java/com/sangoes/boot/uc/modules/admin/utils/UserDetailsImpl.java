package com.sangoes.boot.uc.modules.admin.utils;

import com.sangoes.boot.uc.constants.SecurityConstants;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/15 8:43 PM
 */
@Data
public class UserDetailsImpl implements UserDetails {
    /**
     * 用户主键
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户角色
     */
    private List<SysRole> roleList;


    public UserDetailsImpl(UserDetailsVo userVo) {
        this.userId = String.valueOf(userVo.getId());
        this.username = userVo.getUsername();
        this.status = String.valueOf(userVo.getStatus());
        this.password = userVo.getPassword();
        this.roleList = userVo.getRoles();
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (SysRole role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }

        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     */
    @Override
    public boolean isAccountNonLocked() {
        return !StringUtils.equals(SecurityConstants.USER_STATUS_LOCKED, status);
    }

    /**
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     */
    @Override
    public boolean isEnabled() {
        return StringUtils.equals(SecurityConstants.USER_STATUS_NORMAL, status);
    }
}
