package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    /**
     * 删除字典组
     */
    public interface DeleteDictGroup {

    }


    /**
     * 批量删除字典组
     */
    public interface BatchDeleteDictGroup {

    }

    /**
     * 更新字典组
     */
    public interface UpdateDictGroup {

    }

    @NotNull(message = "字典主键不能为空", groups = {UpdateDictGroup.class})
    @ApiModelProperty(value = "字典主键")
    private Long id;

    @NotNull(message = "字典主键集合不能为空", groups = {BatchDeleteDictGroup.class})
    @ApiModelProperty(value = "字典主键集合")
    private List<Long> dictIds;

    @NotNull(message = "字典主键不能为空", groups = {DeleteDictGroup.class})
    @ApiModelProperty(value = "字典主键")
    private Long dictId;

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

    @ApiModelProperty(value = "是否子字典")
    private boolean subDict;

}
