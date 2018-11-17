package com.sangoes.boot.uc.modules.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/17 12:21 PM
 */
@Data
@Accessors(chain = true)
public class AuthVo implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 菜单主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限编码
     */
    private String authCode;

    /**
     * 权限地址
     */
    private String action;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 1启用 0禁用
     */
    private Integer status;

    /**
     * 菜单描述
     */
    private String des;
}
