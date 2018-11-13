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
 * 角色菜单中间表
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysRoleMenu对象", description = "角色菜单中间表")
public class SysRoleMenu extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "角色主键")
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "菜单主键")
    private Long menuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "权限主键")
    private Long authId;

}
