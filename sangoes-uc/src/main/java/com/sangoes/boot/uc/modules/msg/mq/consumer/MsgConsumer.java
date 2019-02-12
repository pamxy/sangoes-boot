package com.sangoes.boot.uc.modules.msg.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 消息消费者
 *
 * @author jerrychir
 * @date 2019 2019/2/12 11:13 AM
 */
@Slf4j
@Component
public class MsgConsumer {

    /**
     * 消息中心服务
     */
    private final IMsgCenterService msgCenterService;

    @Autowired
    public MsgConsumer(IMsgCenterService msgCenterService) {
        this.msgCenterService = msgCenterService;
    }

    /**
     * 消息消费
     *
     * @param msgJson
     */
    @Async("taskExecutor")
    @RabbitListener(queues = {RabbitConstants.MSG_DIRECT_QUEUE})
    public void msgListenerAutoAck(String msgJson) {
        // json 转 object
        MsgCenter msg = JSONUtil.parseObj(msgJson).toBean(MsgCenter.class);
        // 写入数据库
        msgCenterService.save(msg);
    }
}
