package com.sangoes.boot.uc.modules.admin.service.impl;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 7:45 PM
 */
@Service
public class EncryptServiceImpl implements IEncryptService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 根据手机号码创建RSA的公钥私钥并存入redis
     *
     * @param mobile
     * @return 公钥
     */
    @Override
    public String createRSAKeyByMobile(String mobile) {
        // key
        String key = RSAConstants.MOBILE_RSA_PRIVATE_KEY + mobile;
        long time = RSAConstants.MOBILE_RSA_STORE_TIME;
        // 检查redis中是否有key
        if (redisTemplate.hasKey(key)) {
            // 删除
            redisTemplate.delete(key);
        }
        // 生产
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // 私钥
        String privateEncode = Base64.encode(privateKey.getEncoded());
        // 公钥
        String publicEncode = Base64.encode(publicKey.getEncoded());
        // 保存在redis里
        redisTemplate.opsForValue().set(key, privateEncode, time, TimeUnit.MINUTES);
        return publicEncode;
    }

    @Override
    public String createRSAKeyByRandom(String random) {
        // key
        String key = RSAConstants.RANDOM_RSA_PRIVATE_KEY + random;
        long time = RSAConstants.RANDOM_RSA_STORE_TIME;
        // 检查redis中是否有key
        if (redisTemplate.hasKey(key)) {
            // 删除
            redisTemplate.delete(key);
        }
        // 生产
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // 私钥
        String privateEncode = Base64.encode(privateKey.getEncoded());
        // 公钥
        String publicEncode = Base64.encode(publicKey.getEncoded());
        // 保存在redis里
        redisTemplate.opsForValue().set(key, privateEncode, time, TimeUnit.MINUTES);
        return publicEncode;
    }
}
