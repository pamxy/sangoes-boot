package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.mapper.SysUserMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     */
    @Override
    public boolean signUpByMobile(SignUpDto signUpDto) {
        //验证手机号码是否被注册
        SysUser user = this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, signUpDto.getMobile()));
        if (ObjectUtil.isNull(user)) {
            throw new HandleErrorException("手机号码已被注册");
        }
        //从缓存中获取privateKey
        String privateKey = String.valueOf(redisTemplate.opsForValue().get(RSAConstants.MOBILE_RSA_PRIVATE_KEY));
        String publicKey = String.valueOf(redisTemplate.opsForValue().get(RSAConstants.MOBILE_RSA_PUBLIC_KEY));
        //解密密码
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, privateKey, publicKey);
        String password = StrUtil.str(crypto.decryptFromBase64(signUpDto.getPassword(), KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);

        //创建user
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(signUpDto, sysUser);
        //加密密码
        sysUser.setPassword( encoder.encode(password));
        //写入数据库
        return this.save(sysUser);
    }
}
