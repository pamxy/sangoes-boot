package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/26 6:21 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "字典对象", description = "字典对象")
public class DictDto {
    /**
     * 添加字典组
     */
    public interface AddDictGroup {

    }

    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @NotBlank(message = "字典编码不能为空", groups = {AddDictGroup.class})
    @ApiModelProperty(value = "字典编码")
    private String dictKey;

    @NotBlank(message = "字典名称不能为空", groups = {AddDictGroup.class})
    @ApiModelProperty(value = "字典名称")
    private String dictValue;

    @ApiModelProperty(value = "字典描述")
    private String des;

}
