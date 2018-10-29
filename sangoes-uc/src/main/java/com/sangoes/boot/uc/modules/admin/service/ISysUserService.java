package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param signUpDto
     */
    void signUpByMobile(SignUpDto signUpDto);
}
