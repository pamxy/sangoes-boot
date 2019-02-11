package com.sangoes.boot.uc.modules.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 用户vo
 *
 * @author jerrychir
 * @date 2019 2019/2/11 12:09 PM
 */
@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 更新时间
     */
    private Date updTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 状态 300可以登录 301禁止登陆
     */
    private Integer status;

    /**
     * 性别 110男 111女 112未知
     */
    private Integer gender;

    /**
     * 体重 单位kg
     */
    private Integer weight;
    /**
     * 身高 单位厘米
     */
    private Integer height;
    /**
     * 权限
     */
    private Set<AuthVo> auth;
}
