package com.sangoes.boot.uc.modules.admin.service.impl;

import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.mapper.SysUserMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     */
    @Override
    public void signUpByMobile(SignUpDto signUpDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(signUpDto,sysUser);
        //加密密码
    }
}
