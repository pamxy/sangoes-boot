package com.sangoes.boot.uc.constants;

/**
 * Copyright (c) 2018
 * RSA常量
 *
 * @author jerrychir
 * @date 2018/10/29 10:15 PM
 */
public interface RSAConstants {
    /**
     * 私钥
     */
    String MOBILE_RSA_PRIVATE_KEY = "sangoes:rsa:private:key:mobile:";
    /**
     * 公钥
     */
    String MOBILE_RSA_PUBLIC_KEY = "sangoes:rsa:public:key:mobile:";

    /**
     * key保存时间 单位(天)
     */
    long MOBILE_RSA_STORE_TIME = 1;
}
