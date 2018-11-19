package com.sangoes.boot.uc.modules.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sangoes.boot.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser extends BaseEntity{

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "编码")
    private Long code;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "开放id")
    private String openId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @JsonIgnore
    @ApiModelProperty(value = "加密密码")
    private String password;

    @JsonIgnore
    @ApiModelProperty(value = "加密盐")
    private String salt;

    @ApiModelProperty(value = "注册类型 101PC 102手机 103管理员添加")
    private Integer signupType;

    @ApiModelProperty(value = "登录类型 201PC登录 202手机登录 203为邮箱")
    private Integer loginType;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "状态 300可以登录 301禁止登陆")
    private Integer status;

    @ApiModelProperty(value = "性别 110男 111女 112未知")
    private Integer gender;

    @ApiModelProperty(value = "体重 单位kg")
    private Integer weight;

    @ApiModelProperty(value = "身高 单位厘米")
    private Integer height;

}
