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
 * 用户部门中间表
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserDepart对象", description="用户部门中间表")
public class UserDepart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "部门主键")
    private Long departId;


}
