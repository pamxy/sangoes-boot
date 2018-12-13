/*
 * @Author: jerrychir @sangoes
 * @Date: 2018-11-10 15:55:02
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-10 16:52:52
 */
package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * AuthDto
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "权限对象", description = "权限对象")
public class AuthDto {

    /**
     * AddAuthGroup
     */
    public interface AddAuthGroup {

    }

    /**
     * UpdateAuthGroup
     */
    public interface UpdateAuthGroup {

    }

    /**
     * DeleteAuthGroup
     */
    public interface DeleteAuthGroup {

    }

    /**
     * BatchDeleteAuthGroup
     */
    public interface BatchDeleteAuthGroup {

    }

    @NotNull(message = "权限主键不能为空", groups = {BatchDeleteAuthGroup.class})
    @ApiModelProperty(value = "权限主键")
    private List<Long> authIds;

    @NotNull(message = "权限主键不能为空", groups = {DeleteAuthGroup.class})
    @ApiModelProperty(value = "权限主键")
    private Long authId;

    @NotNull(message = "权限主键不能为空", groups = {UpdateAuthGroup.class})
    @ApiModelProperty(value = "权限主键")
    private Long id;

    @NotNull(message = "菜单主键不能为空", groups = {AddAuthGroup.class})
    @ApiModelProperty(value = "菜单主键")
    private Long menuId;

    @NotBlank(message = "权限名称不能为空", groups = {AddAuthGroup.class})
    @Length(message = "权限名称为2-16为字符", min = 2, max = 16, groups = {AddAuthGroup.class})
    @ApiModelProperty(value = "权限名称")
    private String authName;

    @NotBlank(message = "权限编码不能为空", groups = {AddAuthGroup.class})
    @Length(message = "权限编码为2-30为字符", min = 2, max = 30, groups = {AddAuthGroup.class})
    @ApiModelProperty(value = "权限编码")
    private String authCode;

    @NotBlank(message = "权限地址不能为空", groups = {AddAuthGroup.class})
    @ApiModelProperty(value = "权限地址")
    private String action;

    @NotBlank(message = "请求方法不能为空", groups = {AddAuthGroup.class})
    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "1启用 0禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单描述")
    private String des;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;
}