package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 7:45 PM
 */
@Service
@Slf4j
public class EncryptServiceImpl implements IEncryptService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据手机号码创建RSA的公钥私钥并存入redis
     *
     * @param mobile
     * @return 公钥
     */
    @Override
    public String createRSAKeyByMobile(String mobile) {
        //生产
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        //私钥
        String privateEncode = Base64.encode(privateKey.getEncoded());
        //公钥
        String publicEncode = Base64.encode(publicKey.getEncoded());
        //保存在redis里
        redisTemplate.opsForValue().set(RSAConstants.MOBILE_RSA_PRIVATE_KEY + mobile, privateEncode, RSAConstants.MOBILE_RSA_STORE_TIME, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RSAConstants.MOBILE_RSA_PUBLIC_KEY + mobile, publicEncode, RSAConstants.MOBILE_RSA_STORE_TIME, TimeUnit.DAYS);
        return publicEncode;
    }
}
