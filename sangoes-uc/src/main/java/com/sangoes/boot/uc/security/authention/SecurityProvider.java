package com.sangoes.boot.uc.security.authention;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.sangoes.boot.uc.constants.RSAConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (c) 2018
 * 登录自定义用户名密码
 *
 * @author jerrychir
 * @date 2018/11/15 8:59 PM
 */
@Slf4j
@Component
public class SecurityProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 转换
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        // 查询userDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationToken.getName());
        if (ObjectUtil.isNull(userDetails)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 获取其他信息
        Map details = (LinkedHashMap) authenticationToken.getDetails();

        // TODO 前端密码RSA解密
        // 判断密码是否相同
        String forntPassword = authenticationToken.getCredentials().toString();
        // 解密密码
        String password = decodePassword(RSAConstants.RANDOM_RSA_PRIVATE_KEY + details.get("publicRandom").toString(),
                forntPassword);
        String passwordDb = userDetails.getPassword();
        if (!passwordEncoder.matches(password, passwordDb)) {
            throw new BadCredentialsException("密码不正确");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, passwordDb, userDetails.getAuthorities());
    }

    /**
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
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
