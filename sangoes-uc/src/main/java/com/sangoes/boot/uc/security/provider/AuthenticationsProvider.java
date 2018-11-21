package com.sangoes.boot.uc.security.provider;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.constants.SecurityConstants;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.admin.utils.UserDetailsImpl;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import com.sangoes.boot.uc.security.token.AuthenticationsToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/20 1:40 PM
 */
@Slf4j
public class AuthenticationsProvider implements AuthenticationProvider {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 转换
        AuthenticationsToken token = (AuthenticationsToken) authentication;
        // 根据type分别登录 ACCOUNT 用户名
        if (StrUtil.equals(token.getType(), SecurityConstants.ACCOUNT)) {
            // 查询用户
            UserDetailsVo userDetailsVo = userService.selectUserDetailsByUsername(token.getName());
            log.error("username:{}", token.getName());
            if (ObjectUtil.isNull(userDetailsVo)) {
                throw new UsernameNotFoundException("用户不存在");
            }
            // 转换
            UserDetailsImpl userDetails = new UserDetailsImpl(userDetailsVo);
            // 判断密码是否相同
            String newPassword = token.getCredentials().toString();
            // 解密密码
            String password = decodePassword(RSAConstants.RANDOM_RSA_PRIVATE_KEY + token.getPublicRandom(),
                    newPassword);
            String passwordDb = userDetails.getPassword();
            if (!passwordEncoder.matches(password, passwordDb)) {
                throw new BadCredentialsException("密码不正确");
            }
            return new UsernamePasswordAuthenticationToken(userDetails, passwordDb, userDetails.getAuthorities());
        }
        // 根据type分别登录 MOBILE 手机登录
        if (StrUtil.equals(token.getType(), SecurityConstants.MOBILE)) {
            // 查询用户
            UserDetailsVo userDetailsVo = userService.selectUserDetailsByMobile(token.getName());
            log.error("mobile:{}", token.getName());
            if (ObjectUtil.isNull(userDetailsVo)) {
                throw new UsernameNotFoundException("手机不存在");
            }
            // 转换
            UserDetailsImpl userDetails = new UserDetailsImpl(userDetailsVo);
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }
        // 没有type匹配
        return null;
    }

    /**
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationsToken.class.isAssignableFrom(authentication);
    }

    /**
     * 解密密码
     *
     * @param key
     * @param password
     * @return
     */
    private String decodePassword(String key, String password) {
        // 从缓存中获取privateKey
        String privateKey = String.valueOf(redisTemplate.opsForValue().get(key));
        // 解密密码
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, privateKey, null);
        return StrUtil.str(crypto.decryptFromBase64(password, KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
    }
}
