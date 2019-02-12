package com.sangoes.boot.uc.modules.msg.service.impl;

import cn.hutool.json.JSONUtil;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.common.core.service.IMqService;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import com.sangoes.boot.uc.modules.msg.mapper.MsgCenterMapper;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息中心 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2019-02-11
 */
@Service
public class MsgCenterServiceImpl extends BaseServiceImpl<MsgCenterMapper, MsgCenter> implements IMsgCenterService {

    /**
     * 消息类
     */
    private final IMqService mqService;

    @Autowired
    public MsgCenterServiceImpl(IMqService mqService) {
        this.mqService = mqService;
    }

    /**
     * 发送消息
     *
     * @param msgDto
     */
    @Override
    public void sendMessage(MsgDto msgDto) {
        // 遍历接收者主键
        msgDto.getReceiverIds().forEach(id -> {
            // 创建MsgCenter
            MsgCenter msgCenter = new MsgCenter();
            // 复制
            BeanUtils.copyProperties(msgDto, msgCenter);
            // 设置属性
            msgCenter.setSenderId(AuthUtils.getUserId());
            msgCenter.setSender(AuthUtils.getUserName());
            // 放入队列
            mqService.sendMessage(RabbitConstants.MSG_DIRECT_QUEUE, JSONUtil.toJsonStr(msgCenter));
        });
    }
}
