package com.sangoes.boot.common.core.service;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 队列
 *
 * @author jerrychir
 * @date 2019 2019/2/12 9:08 AM
 */
public interface IMqService {

    /**
     * 发送消息
     *
     * @param routingKey
     * @param object
     */
    void sendMessage(String routingKey, Object object);
}
