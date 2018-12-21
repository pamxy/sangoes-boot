package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * MenuDto 菜单对象
 * @author jerrychir
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

    /**
     * UpdateMenuGroup
     */
    public interface UpdateMenuGroup {

    }

    /**
     * DeleteMenuGroup
     */
    public interface DeleteMenuGroup {

    }

    @NotNull(message = "菜单主键不能为空", groups = {DeleteMenuGroup.class})
    @ApiModelProperty(value = "菜单主键")
    private Long menuId;

    @NotNull(message = "菜单主键不能为空", groups = {UpdateMenuGroup.class})
    @ApiModelProperty(value = "菜单主键")
    private Long id;

    @NotNull(message = "父主键不能为空", groups = {AddMenuGroup.class})
    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @NotBlank(message = "菜单名称不能为空", groups = {AddMenuGroup.class})
    @Length(min = 2, max = 20, message = "菜单名称2-20位", groups = {AddMenuGroup.class})
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @NotBlank(message = "菜单编码不能为空", groups = {AddMenuGroup.class})
    @Length(min = 2, max = 15, message = "菜单编码2-15位", groups = {AddMenuGroup.class})
    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @Length(max = 50, message = "菜单描述最多50位", groups = {AddMenuGroup.class})
    @ApiModelProperty(value = "菜单描述")
    private String des;
}