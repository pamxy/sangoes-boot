package com.sangoes.boot.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 基础vo
 *
 * @author jerrychir
 * @date 2019 2019/2/13 2:37 PM
 */
@Accessors(chain = true)
@Data
public class BaseVo implements Serializable {
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
     * 更新者用户名
     */
    private String updator;
}
