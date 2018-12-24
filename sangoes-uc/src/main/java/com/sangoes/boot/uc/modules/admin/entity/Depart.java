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
 * 部门表
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Depart对象", description="部门表")
public class Depart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父主键 默认-1")
    private Long parentId;

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
