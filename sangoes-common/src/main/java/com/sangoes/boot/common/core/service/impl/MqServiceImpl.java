package com.sangoes.boot.common.core.service.impl;

import com.sangoes.boot.common.core.service.IMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 队列通用方法
 *
 * @author jerrychir
 * @date 2019 2019/2/12 9:08 AM
 */
@Service
public class MqServiceImpl implements IMqService {

    /**
     * 队列bean
     */
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MqServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     *
     * @param routingKey
     * @param object
     */
    @Override
    public void sendMessage(String routingKey, Object object) {
        rabbitTemplate.convertAndSend(routingKey, object);
    }
}
