package com.sangoes.boot.uc.modules.msg.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import com.sangoes.boot.uc.modules.msg.vo.MsgCountVo;
import com.sangoes.boot.uc.modules.msg.vo.MsgTypeVo;

import java.util.Map;

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

    /**
     * 查询消息分页
     *
     * @param params
     * @return
     */
    MsgTypeVo pageMsg(Map<String, Object> params);

    /**
     * 获取当前用户消息数量
     *
     * @return
     */
    MsgCountVo countMsg();

}
