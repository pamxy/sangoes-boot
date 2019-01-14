package com.sangoes.boot.uc.modules.admin.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.lang.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Copyright (c) 2018 加密管理 前端控制器
 *
 * @author jerrychir
 * @date 2018/10/29 9:01 PM
 */
@RestController
@Api("加密管理")
@RequestMapping("/api/encrypt")
public class EncryptController extends ApiController {

    @Autowired
    private IEncryptService encryptService;

    /**
     * 根据手机号码获取公私钥
     *
     * @param mobile
     * @return
     */
    @RecLog("根据手机号码获取公私钥")
    @GetMapping("/mobile/{mobile}")
    @ApiOperation(value = "根据手机号码获取公私钥", notes = "返回公钥")
    @ResponseBody
    public Result<String> createRSAKeyByMobile(@PathVariable String mobile) {
        // 判读mobile
        boolean isMobile = Validator.isMobile(mobile);
        if (!isMobile) {
            return Result.failed("手机号码不正确");
        }
        // 获取公钥
        String publicKey = encryptService.createRSAKeyByMobile(mobile);
        return Result.success(publicKey, "成功");
    }

    /**
     * 根据随机数获取公私钥
     *
     * @param random
     * @return
     */
    @RecLog("根据随机数获取公私钥")
    @GetMapping("/rsa/{random}")
    @ApiOperation(value = "根据随机数获取公私钥", notes = "返回公钥")
    @ResponseBody
    public Result<String> createRSAKeyByRandom(@PathVariable String random) {
        // isEmpty
        boolean isEmpty = Validator.isEmpty(random);
        if (isEmpty) {
            return Result.failed("随机数不能为空");
        }
        // 获取公钥
        String publicKey = encryptService.createRSAKeyByRandom(random);
        return Result.success(publicKey, "成功");
    }
}
