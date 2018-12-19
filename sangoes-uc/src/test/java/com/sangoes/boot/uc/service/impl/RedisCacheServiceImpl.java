package com.sangoes.boot.uc.service.impl;

import com.sangoes.boot.common.cache.redis.annotation.CacheRegexRemove;
import com.sangoes.boot.uc.entity.User;
import com.sangoes.boot.uc.service.IRedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/19 1:04 PM
 */
@Slf4j
@Service
public class RedisCacheServiceImpl implements IRedisCacheService {

    //    @Caching(
//            cacheable = {@Cacheable(value = "user",key = "'test:user:'+#user.getAge()")}
//    )
//    @Cache(value = "user",key = "test:user:jerry")
    @Cacheable(value = "user", key = "'test:user:'+#user.getAge()")
    @Override
    public User saveUser(User user) {
        log.info("save user");
        return user;
    }

    @Cacheable(value = "role", key = "'role:user:'+#user.getName()")
    @Override
    public User saveUser1(User user) {
        return user;
    }

    @CacheRegexRemove(values = "user")
//    @CacheEvict(value = "user", key = "'test:'+#root.methodName")
//    @Caching(evict = {@CacheEvict(value = "user")})
    @Override
    public void removeUser() {

    }
}
