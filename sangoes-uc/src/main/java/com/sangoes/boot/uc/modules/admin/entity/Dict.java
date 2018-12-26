package com.sangoes.boot.uc.modules.admin.entity;

import java.math.BigDecimal;
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
 * 字典表
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Dict对象", description="字典表")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

    @ApiModelProperty(value = "字典编码")
    private String dictKey;

    @ApiModelProperty(value = "字典名称")
    private String dictValue;

    @ApiModelProperty(value = "字典描述")
    private String des;


}
