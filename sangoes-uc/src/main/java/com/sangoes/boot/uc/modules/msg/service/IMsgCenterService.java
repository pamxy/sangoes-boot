package com.sangoes.boot.uc.modules.msg.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;

/**
 * <p>
 * 消息中心 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2019-02-11
 */
public interface IMsgCenterService extends IBaseService<MsgCenter> {

    /**
     * 发送消息
     *
     * @param msgDto
     */
    void sendMessage(MsgDto msgDto);
}
