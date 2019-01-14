package com.sangoes.boot.uc.modules.admin.controller;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.service.ICaptchaService;

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
 * Copyright (c) 2018 验证码 前端控制器
 *
 * @author jerrychir
 * @date 2018/10/30 8:08 PM
 */
@RestController
@Api("验证码管理")
@RequestMapping("/api/captcha")
public class CaptchaController extends ApiController {

    @Autowired
    private ICaptchaService captchaService;

    /**
     * 根据手机号码发送验证码
     *
     * @param mobile
     * @return
     */
    @RecLog("根据手机号码发送验证码")
    @GetMapping("/sms/{mobile}")
    @ApiOperation(value = "根据手机号码发送验证码", notes = "返回公钥")
    @ResponseBody
    public Result<String> sendCaptchaBySms(@PathVariable String mobile) {
        // 判读mobile
        boolean isMobile = Validator.isMobile(mobile);
        if (!isMobile) {
            return Result.failed("手机号码不正确");
        }
        // 发送验证码并返回公钥
        String publicKey = captchaService.sendCaptchaBySms(mobile);
        return Result.success(publicKey, "成功");
    }

    /**
     * 生成随机验证码图片
     * 
     * @param random
     * @param response
     * @return
     */
    @RecLog("生成随机验证码图片")
    @GetMapping("/image/{random}")
    @ApiOperation(value = "生成随机验证码图片", notes = "返回图片流")
    @ResponseBody
    public void generateImageCaptcha(@PathVariable String random, HttpServletResponse response) {
        // 生成验证码
        captchaService.generateCaptcha(random, response);
    }
}
