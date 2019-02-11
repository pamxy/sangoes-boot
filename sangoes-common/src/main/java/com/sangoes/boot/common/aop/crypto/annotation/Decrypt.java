package com.sangoes.boot.common.aop.crypto.annotation;

import com.sangoes.boot.common.utils.crypto.CryptoType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 解密
 *
 * @author jerrychir
 * @date 2019 2019/1/16 7:19 PM
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Decrypt {

    /**
     * 如果选择的 RSA 加/解密算法，那么 key 为必填项
     *
     * @return CryptoType
     */
    CryptoType type() default CryptoType.AES;

    /**
     * 可选，如果未配置则采用全局的key
     *
     * @return String
     */
    String key() default "";

    /**
     * 描述信息
     *
     * @return String
     */
    String description() default "";
}
