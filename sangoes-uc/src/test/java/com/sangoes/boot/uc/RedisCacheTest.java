package com.sangoes.boot.uc;

import com.sangoes.boot.common.core.config.RedisTTLConfig;
import com.sangoes.boot.uc.entity.User;
import com.sangoes.boot.uc.service.IRedisCacheService;
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
 * @date 2018 2018/12/19 1:04 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisCacheTest {

    @Autowired
    private IRedisCacheService redisCacheService;

    @Autowired
    private RedisTTLConfig ttlConfig;

//    @Autowired
//    private RedisUtil redisUtil;

    @Test
    public void testSave() {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(11 + i);
            user.setName("Jerry" + i);
            user.setPassword("123446" + i);
            redisCacheService.saveUser(user);
            redisCacheService.saveUser1(user);
        }

    }

    @Test
    public void testRemove() {
        redisCacheService.removeUser();
    }

    @Test
    public void testRedisUtil() {
//        redisUtil.set("ddd", "ddd");
    }

    @Test
    public void testTTL() {
        log.error("ttl:{}", ttlConfig.getTtl());
    }

}
