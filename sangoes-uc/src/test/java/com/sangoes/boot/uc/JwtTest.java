package com.sangoes.boot.uc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/5 11:36 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    // @Autowired
    // private JwtTokenProvider jwtTokenProvider;

    @Test
    public void generateToken() {
        // System.out.println(jwtTokenProvider.createToken("jerrychir"));
    }
}
