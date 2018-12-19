package com.sangoes.boot.uc.service;

import com.sangoes.boot.uc.entity.User;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/19 1:04 PM
 */
public interface IRedisCacheService {

    public User saveUser(User user);
    public User saveUser1(User user);

    public void removeUser();
}
