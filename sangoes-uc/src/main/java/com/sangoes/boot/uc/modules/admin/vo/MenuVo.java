package com.sangoes.boot.uc.modules.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/17 11:05 AM
 */
@Data
@Accessors(chain = true)
public class MenuVo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 父级
     */
    private Long parentId;

    private String menuName;

    private String menuCode;

    private String url;

    private String icon;

    private Integer status;

    private String des;
}
