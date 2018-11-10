package com.sangoes.boot.uc.modules.admin.entity;

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
 * @since 2018-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysUserRole对象", description = "")
public class SysUserRole extends BaseEntity {

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "角色主键")
    private Long roleId;

}
