package com.sangoes.boot.uc.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.sangoes.boot.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限中间表
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysRoleAuth对象", description = "角色权限中间表")
public class SysRoleAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    private Long roleId;

    @ApiModelProperty(value = "权限主键")
    private Long authId;

    @ApiModelProperty(value = "菜单主键")
    private Long menuId;

}
