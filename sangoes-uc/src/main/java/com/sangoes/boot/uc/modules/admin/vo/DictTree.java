package com.sangoes.boot.uc.modules.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sangoes.boot.common.vo.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 字典树形
 *
 * @author jerrychir
 * @date 2018 2018/12/28 12:02 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DictTree extends TreeNode {
    /**
     * 标题
     */
    private String name;
    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    /**
     * 描述
     */
    private String des;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long sort;
}
