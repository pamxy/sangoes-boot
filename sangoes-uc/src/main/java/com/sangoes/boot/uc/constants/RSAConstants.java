package com.sangoes.boot.uc.constants;

/**
 * Copyright (c) 2018 RSA常量
 *
 * @author jerrychir
 * @date 2018/10/29 10:15 PM
 */
public interface RSAConstants {
    /**
     * 手机号码私钥
     */
    String MOBILE_RSA_PRIVATE_KEY = "sangoes:rsa:private:key:mobile:";
    /**
     * 手机号码公钥
     */
    String MOBILE_RSA_PUBLIC_KEY = "sangoes:rsa:public:key:mobile:";

    /**
     * 手机号码key保存时间 单位(分钟)
     */
    long MOBILE_RSA_STORE_TIME = 10;

    /**
     * 随机数私钥
     */
    String RANDOM_RSA_PRIVATE_KEY = "sangoes:rsa:private:key:random:";
    /**
     * 随机数公钥
     */
    String RANDOM_RSA_PUBLIC_KEY = "sangoes:rsa:public:key:random:";

    /**
     * 随机key保存时间 单位(分钟)
     */
    long RANDOM_RSA_STORE_TIME = 10;
}
