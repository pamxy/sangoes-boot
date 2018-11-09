package com.sangoes.boot.uc.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.sangoes.boot.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="菜单")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "1启用 0禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单描述")
    private String des;


}
