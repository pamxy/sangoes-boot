package com.sangoes.boot.uc.modules.admin.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;
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
 * 加密管理 前端控制器
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
    private IEncryptService encryptService;

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
        //判读mobile
        boolean isMobile = Validator.isMobile(mobile);
        if (!isMobile){
            return R.failed("手机号码不正确");
        }
        //获取公钥
        String publicKey = encryptService.createRSAKeyByMobile(mobile);
        return R.ok(publicKey);
    }
}
