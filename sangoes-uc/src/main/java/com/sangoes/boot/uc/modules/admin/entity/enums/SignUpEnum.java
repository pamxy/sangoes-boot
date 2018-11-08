package com.sangoes.boot.uc.modules.admin.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * SignUpEnum
 */
public enum SignUpEnum implements IEnum<Integer> {
    PC(101, "PC"), MOBILE(102, "手机"), ADMIN(103, "管理员添加");
    private int value;
    private String desc;

    SignUpEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}