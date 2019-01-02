package com.sangoes.boot.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/29 11:13 AM
 */
@Data
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = -7582664785863208598L;
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 更新时间
     */
    private Date updTime;

    /**
     * 创建者用户名
     */
    private String creator;

    /**
     * 创建者主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long creatorId;

    /**
     * 更新者用户名
     */
    private String updator;

    /**
     * 更新者主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatorId;

    /**
     * 删除 1未删除 0删除 逻辑删除加 @TableLogic
     */
    // @TableLogic
    private Integer deleted;

    /**
     * 排序
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sort;
}
