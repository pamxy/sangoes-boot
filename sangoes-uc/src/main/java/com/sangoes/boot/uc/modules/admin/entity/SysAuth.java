package com.sangoes.boot.uc.modules.admin.entity;

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
 * 
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "权限对象", description = "权限对象")
public class SysAuth extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单主键")
    private Long menuId;

    @ApiModelProperty(value = "权限名称")
    private String authName;

    @ApiModelProperty(value = "权限编码")
    private String authCode;

    @ApiModelProperty(value = "权限地址")
    private String action;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "1启用 0禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单描述")
    private String des;

}
