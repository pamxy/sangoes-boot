package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.mapper.SysUserMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

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
        //验证手机号码是否被注册
        SysUser user = this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, signUpDto.getMobile()));
        if (ObjectUtil.isNull(user)){
            throw new HandleErrorException("手机号码已被注册");
        }
        //解密密码
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA);
//        crypto.
        //创建user
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(signUpDto,sysUser);
        //随机生成用户名
        sysUser.setUsername(RandomUtil.randomString(8));
        //加密密码
    }
}
