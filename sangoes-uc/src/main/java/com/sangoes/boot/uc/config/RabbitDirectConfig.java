package com.sangoes.boot.uc.config;

import com.sangoes.boot.common.constants.RabbitConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * Rabbit Direct配置
 *
 * @author jerrychir
 * @date 2019 2019/1/11 4:58 PM
 */
@Configuration
public class RabbitDirectConfig {
    /**
     * 日志队列
     *
     * @return
     */
    @Bean
    public Queue logQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(RabbitConstants.LOG_DIRECT_QUEUE, true);
    }

    /**
     * 消息队列
     *
     * @return
     */
    @Bean
    public Queue msgQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(RabbitConstants.MSG_DIRECT_QUEUE, true);
    }
}
