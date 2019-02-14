package com.sangoes.boot.uc.modules.msg.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 消息类型 1消息 2通知 3待办 4手机推送
 *
 * @author jerrychir
 * @date 2019 2019/2/11 10:00 PM
 */
public enum MsgTypeEnum implements IEnum<Integer> {
    MSG(1, "消息"),
    NOTIFI(2, "通知"),
    AGENDA(3, "待办"),
    PUSH(4, "推送");

    private int value;
    private String desc;

    MsgTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
