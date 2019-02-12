package com.sangoes.boot.uc.modules.msg.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.common.core.service.IMqService;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import com.sangoes.boot.uc.modules.msg.entity.enums.MsgTypeEnum;
import com.sangoes.boot.uc.modules.msg.mapper.MsgCenterMapper;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public MsgCenterServiceImpl(IMqService mqService, ISysUserService userService) {
        this.mqService = mqService;
        this.userService = userService;
    }

    /**
     * 角色服务类
     */
    private final ISysUserService userService;

    /**
     * 发送消息
     *
     * @param msgDto
     */
    @Override
    public void sendMessage(MsgDto msgDto) {
        // 创建set
        Set<SysUser> userSet = new HashSet<SysUser>();
        // 角色编码
        List<String> roleCodes = msgDto.getRoleCode();
        // 通过角色编码查询用户
        roleCodes.forEach(roleCode -> {
            List<SysUser> users = userService.listByRoleCode(roleCode);
            // 删除list中的null
            users.remove(null);
            CollUtil.addAll(userSet, users);
        });

        // 遍历接收者主键
        userSet.forEach((user) -> {
            // 创建MsgCenter
            MsgCenter msgCenter = new MsgCenter();
            // 复制
            BeanUtils.copyProperties(msgDto, msgCenter);
            // 设置属性
            msgCenter.setSenderId(AuthUtils.getUserId());
            msgCenter.setSender(AuthUtils.getUserName());
            msgCenter.setReceiver(user.getUsername());
            msgCenter.setReceiverId(user.getId());
            // 放入队列
            mqService.sendMessage(RabbitConstants.MSG_DIRECT_QUEUE, JSONUtil.toJsonStr(msgCenter));
        });
    }
}
