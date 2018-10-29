package com.sangoes.boot.uc;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

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

    @Test
    public void testPasswod() {
        String result = stringEncryptor.encrypt("Sangoes123456");
        System.out.println(result);
    }
    @Test
    public void rsaKey(){
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        System.out.println("privateKey:"+ Base64.encode(privateKey.getEncoded()));
        System.out.println("publicKey:"+ Base64.encode(publicKey.getEncoded()));
    }
}
