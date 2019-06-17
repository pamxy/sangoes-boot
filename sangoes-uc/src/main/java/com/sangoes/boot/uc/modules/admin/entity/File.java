package com.sangoes.boot.uc.modules.admin.entity;

import com.sangoes.boot.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件管理/附件管理
 * </p>
 *
 * @author jerrychir
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "File对象", description = "文件管理/附件管理")
public class File extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名")
    private String title;

    @ApiModelProperty(value = "文件缀")
    private String extName;

    @ApiModelProperty(value = "总页数")
    private Integer totalPage;

    @ApiModelProperty(value = "源文件路径")
    private String originalPath;

    @ApiModelProperty(value = "转换文件路径")
    private String convertPath;

}
