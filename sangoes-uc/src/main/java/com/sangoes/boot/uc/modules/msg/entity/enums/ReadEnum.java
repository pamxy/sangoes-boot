package com.sangoes.boot.uc.modules.msg.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 读状态 0未读 1已读
 *
 * @author jerrychir
 * @date 2019 2019/2/11 10:07 PM
 */
public enum ReadEnum implements IEnum<Integer> {
    UNREAD(0, "未读"),
    READ(1, "已读");

    private int value;
    private String desc;

    ReadEnum(final int value, final String desc) {
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
