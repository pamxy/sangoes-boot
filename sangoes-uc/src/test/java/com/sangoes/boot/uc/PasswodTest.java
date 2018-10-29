package com.sangoes.boot.uc;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
