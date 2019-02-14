package com.sangoes.boot.uc.modules.msg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.common.constants.RabbitConstants;
import com.sangoes.boot.common.core.service.IMqService;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.common.utils.page.Pagination;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import com.sangoes.boot.uc.modules.msg.entity.enums.ReadEnum;
import com.sangoes.boot.uc.modules.msg.mapper.MsgCenterMapper;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import com.sangoes.boot.uc.modules.msg.vo.MsgCountVo;
import com.sangoes.boot.uc.modules.msg.vo.MsgTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    /**
     * 查询消息分页
     *
     * @param params
     * @return
     */
    @Override
    public MsgTypeVo pageMsg(Map<String, Object> params) {
        // 获取type
        Object typeObj = params.get("type");
        params.remove("type");
        if (ObjectUtil.isNull(typeObj)) {
            throw new HandleErrorException("消息类型(type)不能为空");
        }
        Integer type = Integer.parseInt(typeObj.toString());
        // 获取user id
        Object userIdObj = params.get("userId");
        // 转换
        PageQuery pageQuery = new PageQuery(params);
        // 构建分页
        Page<MsgCenter> page = new Page<>(pageQuery.getCurrent(), pageQuery.getPageSize());
        // 构建条件
        QueryWrapper<MsgCenter> queryWrapper = this.pageQueryCondtion(pageQuery);
        // type
        queryWrapper.lambda().eq(MsgCenter::getMsgType, type);
        // user id
        if (ObjectUtil.isNotNull(userIdObj)) {
            long userId = Long.parseLong(userIdObj.toString());
            queryWrapper.lambda().eq(MsgCenter::getReceiverId, userId);
        }
        // 查询
        IPage<MsgCenter> selectPage = baseMapper.selectPage(page, queryWrapper);

        // 返回结果
        Pagination pagination = new Pagination(selectPage.getTotal(), selectPage.getSize(), selectPage.getCurrent());
        // 分页
        PageData<MsgCenter> pageData = new PageData<>(pagination, selectPage.getRecords());
        // 查询当前用户未读数量
        int unreadCount = this.count(new QueryWrapper<MsgCenter>().lambda()
                .eq(MsgCenter::getReaded, ReadEnum.UNREAD.getValue())
                .eq(MsgCenter::getMsgType, type)
                .eq(MsgCenter::getReceiverId, AuthUtils.getUserId()));
        // 封装
        MsgTypeVo msgTypeVo = new MsgTypeVo();
        msgTypeVo.setPage(pageData);
        msgTypeVo.setUnreadCount(unreadCount);
        return msgTypeVo;
    }

    /**
     * 获取当前用户消息数量
     *
     * @return
     */
    @Override
    public MsgCountVo countMsg() {
        // 查询当前用户未读数量
        int unreadCount = this.count(new QueryWrapper<MsgCenter>().lambda()
                .eq(MsgCenter::getReaded, ReadEnum.UNREAD.getValue())
                .eq(MsgCenter::getReceiverId, AuthUtils.getUserId()));
        // 查询当前已读数量
        int readCount = this.count(new QueryWrapper<MsgCenter>().lambda()
                .eq(MsgCenter::getReaded, ReadEnum.READ.getValue())
                .eq(MsgCenter::getReceiverId, AuthUtils.getUserId()));
        // 封装
        MsgCountVo msgCountVo = new MsgCountVo();
        msgCountVo.setUnreadCount(unreadCount);
        msgCountVo.setReadCount(readCount);
        return msgCountVo;
    }

    /**
     * 更新消息
     *
     * @param msgDto
     */
    @Override
    public void updateMsg(MsgDto msgDto) {
        // 创建MsgCenter
        MsgCenter msgCenter = new MsgCenter();
        // 复制
        BeanUtil.copyProperties(msgDto, msgCenter, CopyOptions.create().setIgnoreNullValue(true));
        // 更新
        boolean flag = this.updateById(msgCenter);
        if (!flag) {
            throw new HandleErrorException("更新失败");
        }
    }
}
