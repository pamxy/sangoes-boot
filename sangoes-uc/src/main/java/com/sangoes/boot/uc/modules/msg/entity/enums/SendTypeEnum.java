package com.sangoes.boot.uc.modules.msg.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 发送类型 1定向 2群发 3广播
 *
 * @author jerrychir
 * @date 2019 2019/2/11 10:17 PM
 */
public enum SendTypeEnum implements IEnum<Integer> {
    DIRECT(1, "定向"),
    MASS(1, "群发"),
    CAST(1, "广播");

    private int value;
    private String desc;

    SendTypeEnum(final int value, final String desc) {
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
