package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.RandomUtil;

import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.uc.constants.CaptchaConstants;
import com.sangoes.boot.uc.modules.admin.service.ICaptchaService;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 8:13 PM
 */
@Service
@Slf4j
public class CaptchaServiceImpl implements ICaptchaService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private IEncryptService encryptService;

    /**
     * 根据手机号码发送验证码并返回公钥
     *
     * @param mobile 手机号码
     * @return 公钥
     */
    @Override
    public String sendCaptchaBySms(String mobile) {
        String captchaConstant = CaptchaConstants.CAPTCHA_MOBILE_SMS + mobile;
        // 检测是否有mobile对应的redis缓存
        boolean hasKey = redisTemplate.hasKey(captchaConstant).booleanValue();
        if (hasKey) {
            // 删除
            redisTemplate.delete(captchaConstant);
        }
        // 生产随机验证码
        String captcha = RandomUtil.randomNumbers(CaptchaConstants.CAPTCHA_RANDOM_COUNT);
        log.info("验证码:" + captcha);
        // TODO 短信发送逻辑 最好放在队列里
        // redis缓存验证码
        redisTemplate.opsForValue().set(captchaConstant, captcha, CaptchaConstants.CAPTCHA_EXPIRE_TIME,
                TimeUnit.MINUTES);
        // 生成公钥私钥
        String publicKey = encryptService.createRSAKeyByMobile(mobile);
        return publicKey;
    }

    @Override
    public void generateCaptcha(String random, HttpServletResponse response) {
        // 生成图片验证码
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(110, 36);
        // 得到验证码
        String code = captcha.getCode();
        // 保存在redis
        redisTemplate.opsForValue().set(CaptchaConstants.CAPTCHA_IMAGE + random, code,
                CaptchaConstants.CAPTCHA_IMAGE_EXPIRE_TIME, TimeUnit.MINUTES);
        // 写入流
        try {
            ServletOutputStream out = response.getOutputStream();
            captcha.write(out);
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            throw new HandleErrorException("生成验证码失败");
        }
    }
}
