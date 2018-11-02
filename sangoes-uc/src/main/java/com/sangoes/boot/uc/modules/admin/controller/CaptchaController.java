package com.sangoes.boot.uc.modules.admin.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.service.ICaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2018
 * 验证码 前端控制器
 *
 * @author jerrychir
 * @date 2018/10/30 8:08 PM
 */
@RestController
@Slf4j
@Api("验证码管理")
@RequestMapping("captcha")
public class CaptchaController extends ApiController {

    @Autowired
    private ICaptchaService captchaService;

    /**
     * 根据手机号码发送验证码
     *
     * @param mobile
     * @return
     */
    @GetMapping("/sms/{mobile}")
    @ApiOperation(value = "根据手机号码发送验证码", notes = "返回公钥")
    @ResponseBody
    public Result<String> sendCaptchaBySms(@PathVariable String mobile) {
        //判读mobile
        boolean isMobile = Validator.isMobile(mobile);
        if (!isMobile) {
            return Result.failed("手机号码不正确");
        }
        //发送验证码并返回公钥
        String publicKey = captchaService.sendCaptchaBySms(mobile);
        return Result.success(publicKey,"成功");
    }
}
