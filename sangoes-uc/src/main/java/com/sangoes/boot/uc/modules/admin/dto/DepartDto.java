package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/21 7:11 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "权限对象", description = "权限对象")
public class DepartDto {

    /**
     * 添加部门组
     */
    public interface AddDepartGroup {

    }

    @NotNull(message = "父主键不能为空", groups = {AddDepartGroup.class})
    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @NotBlank(message = "部门名称不能为空", groups = {AddDepartGroup.class})
    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "座机电话")
    private Integer telephone;

    @ApiModelProperty(value = "负责人主键")
    private Long leaderId;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "描述")
    private String des;
}
