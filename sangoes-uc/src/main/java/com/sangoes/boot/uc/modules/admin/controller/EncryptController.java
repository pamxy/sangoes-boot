package com.sangoes.boot.uc.modules.admin.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.sangoes.boot.uc.constants.RSAConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2018
 * 加密管理
 *
 * @author jerrychir
 * @date 2018/10/29 9:01 PM
 */
@RestController
@Slf4j
@Api("加密管理")
@RequestMapping("encrypt")
public class EncryptController extends ApiController {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 根据手机号码获取公私钥
     *
     * @param mobile
     * @return
     */
    @GetMapping("/mobile/{mobile}")
    @ApiOperation(value = "根据手机号码获取公私钥", notes = "返回公钥")
    @ResponseBody
    public R<String> createRSAKeyByMobile(@PathVariable String mobile) {
        //生产
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        //私钥
        String privateEncode = Base64.encode(privateKey.getEncoded());
        //公钥
        String publicEncode = Base64.encode(publicKey.getEncoded());
        //保存在redis里
        redisTemplate.opsForValue().set(RSAConstants.MOBILE_RSA_PRIVATE_KEY + mobile, privateEncode, 1, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RSAConstants.MOBILE_RSA_PUBLIC_KEY + mobile, publicEncode, 1, TimeUnit.DAYS);
        return R.ok(publicEncode);
    }
}
