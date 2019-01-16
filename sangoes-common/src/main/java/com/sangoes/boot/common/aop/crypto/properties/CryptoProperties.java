package com.sangoes.boot.common.aop.crypto.properties;

import com.sangoes.boot.common.utils.crypto.CryptoType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/16 7:27 PM
 */
@Configuration
@Data
@Validated
@ConfigurationProperties(prefix = "sangoes.crypto")
public class CryptoProperties {

    private Encrypt encrypt;

    private Decrypt decrypt;

    private String encoding = "UTF-8";

    @Data
    @ConfigurationProperties(prefix = "sangoes.crypto.encrypt")
    public static class Encrypt {
        @NotNull
        private String key;
        @NotNull
        private CryptoType type;
    }

    @Data
    @ConfigurationProperties(prefix = "sangoes.crypto.decrypt")
    public static class Decrypt {

        @NotNull
        private String key;
        @NotNull
        private CryptoType type;
    }
}
