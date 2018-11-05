package com.sangoes.boot.uc.modules.admin.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2018 加密管理 前端控制器
 *
 * @author jerrychir
 * @date 2018/10/29 9:01 PM
 */
@RestController
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
