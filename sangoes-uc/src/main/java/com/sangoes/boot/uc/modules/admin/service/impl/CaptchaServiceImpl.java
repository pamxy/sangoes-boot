package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.sangoes.boot.uc.constants.CaptchaConstants;
import com.sangoes.boot.uc.modules.admin.service.ICaptchaService;
import com.sangoes.boot.uc.modules.admin.service.IEncryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
        //生产随机验证码
        String captcha = RandomUtil.randomNumbers(CaptchaConstants.CAPTCHA_RANDOM_COUNT);
        log.info("验证码:", captcha);
        //TODO 短信发送逻辑 最好放在队列里
        //redis缓存验证码
        redisTemplate.opsForValue().set(CaptchaConstants.CAPTCHA_MOBILE_SMS + mobile, captcha, CaptchaConstants.CAPTCHA_EXPIRE_TIME, TimeUnit.MINUTES);
        //生成公钥私钥
        String publicKey = encryptService.createRSAKeyByMobile(mobile);
        return publicKey;
    }
}
