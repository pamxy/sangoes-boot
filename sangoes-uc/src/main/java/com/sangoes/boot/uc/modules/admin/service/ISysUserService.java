package com.sangoes.boot.uc.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     */
    Result signUpByMobile(SignUpDto signUpDto);

    /**
     * 根据手机号码登陆
     *
     * @param signInDto
     * @return
     */
    Result signinByMobile(SignInDto signInDto);
}
