package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.constants.CaptchaConstants;
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
    public Result signUpByMobile(SignUpDto signUpDto) {
        //验证码
        String captchaConstant = CaptchaConstants.CAPTCHA_MOBILE_SMS + signUpDto.getMobile();
        //检测是否有mobile对应的redis缓存
        boolean hasKey = redisTemplate.hasKey(captchaConstant).booleanValue();
        if (!hasKey) {
            throw new HandleErrorException("验证码不存在或过期");
        }
        //删除验证码
        redisTemplate.delete(captchaConstant);
        //验证手机号码是否被注册
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, signUpDto.getMobile()));
        if (!ObjectUtil.isNull(user)) {
            throw new HandleErrorException("手机号码已注册");
        }
        //验证用户名是否被存在
        SysUser userName = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, signUpDto.getUsername()));
        if (!ObjectUtil.isNull(userName)) {
            throw new HandleErrorException("用户名已注册");
        }
        //从缓存中获取privateKey
        String privateKey = String.valueOf(redisTemplate.opsForValue().get(RSAConstants.MOBILE_RSA_PRIVATE_KEY + signUpDto.getMobile()));
        String publicKey = String.valueOf(redisTemplate.opsForValue().get(RSAConstants.MOBILE_RSA_PUBLIC_KEY + signUpDto.getMobile()));
        //解密密码
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, privateKey, publicKey);
        String password = StrUtil.str(crypto.decryptFromBase64(signUpDto.getPassword(), KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);

        //创建user
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(signUpDto, sysUser);
        //设置随机真实姓名
        sysUser.setRealName(RandomUtil.randomString(8));
        //加密密码
        sysUser.setPassword(encoder.encode(password));
        //写入数据库
        boolean save = this.save(sysUser);
        if (!save) {
            throw new HandleErrorException("注册失败");
        }
        return Result.success("注册成功");
    }
}
