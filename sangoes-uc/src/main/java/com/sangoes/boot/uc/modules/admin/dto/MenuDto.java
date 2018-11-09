package com.sangoes.boot.uc.modules.admin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MenuDto 菜单对象
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "菜单对象", description = "菜单对象")
public class MenuDto {

    /**
     * InnerMenuDto
     */
    public interface AddMenuGroup {

    }

    @NotNull(message = "父主键不能为空", groups = { AddMenuGroup.class })
    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @NotBlank(message = "菜单名称不能为空", groups = { AddMenuGroup.class })
    @Length(min = 2, max = 20, message = "菜单名称2-20位", groups = { AddMenuGroup.class })
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @NotBlank(message = "菜单编码不能为空", groups = { AddMenuGroup.class })
    @Length(min = 2, max = 15, message = "菜单编码2-15位", groups = { AddMenuGroup.class })
    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "图标")
    private String icon;

    @Length(max = 50, message = "菜单描述最多50位", groups = { AddMenuGroup.class })
    @ApiModelProperty(value = "菜单描述")
    private String des;
}