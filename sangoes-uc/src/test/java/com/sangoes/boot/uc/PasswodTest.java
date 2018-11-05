package com.sangoes.boot.uc;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/29 6:30 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswodTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswod() {
        String result = stringEncryptor.encrypt("Sangoes123456");
        System.out.println(result);
    }

    @Test
    public void rsaKey() {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        String privateKey = Base64.encode(pair.getPrivate().getEncoded());
        String publicKey = Base64.encode(pair.getPublic().getEncoded());

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        String a = "我是一只小小狗";

        // 加密
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, privateKey, publicKey);
        String encrypt = Base64.encode(crypto.encrypt(a, KeyType.PublicKey));

        // 解密
        byte[] bytes = crypto.decryptFromBase64(encrypt, KeyType.PrivateKey);
        System.out.println(StrUtil.str(bytes, CharsetUtil.CHARSET_UTF_8));

    }

    @Test
    public void testBase64() {
        String encode = Base64.encode("sangoes-boot");
        System.out.println(encode);
    }

    @Test
    public void testPass() {
        System.out.println(passwordEncoder.encode("123456"));
    }

}
