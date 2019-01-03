package com.sangoes.boot.uc;

import com.sangoes.boot.common.core.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/3 5:20 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedisSave() {
        redisUtil.set("dddddd","eeee",100);
        log.info("ddddd");
    }
}
