package com.sangoes.boot.uc.constants;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/30 9:04 PM
 */
public interface CaptchaConstants {

    /**
     * 手机号码缓存验证码
     */
    String CAPTCHA_MOBILE_SMS = "sangoes:captcha:sms:mobile:";

    /**
     * 随机验证码的个数
     */
    int CAPTCHA_RANDOM_COUNT = 6;

    /**
     * 验证码保存的时间 (分钟)
     */
    long CAPTCHA_EXPIRE_TIME = 5;
    /**
     * 图片验证码
     */
    String CAPTCHA_IMAGE = "sangoes:captcha:image:random:";

    /**
     * 图片验证码保存的时间 (分钟)
     */
    long CAPTCHA_IMAGE_EXPIRE_TIME = 5;

    /**
     * 验证码
     */
    String CAPTCHA = "captcha";

    /**
     * 验证码随机数
     */
    String CAPTCHA_RANDOM = "captchaRandom";

    /**
     * 公共随机数
     */
    String PUBLIC_RANDOM = "publicRandom";
    /**
     * 登录类型
     */
    String SIGNIN_TYPE = "signinType";
}
