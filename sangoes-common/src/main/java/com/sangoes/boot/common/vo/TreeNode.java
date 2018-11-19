/*
 * @Author: jerrychir
 * @Date: 2018-11-09 15:11:44
 * @Last Modified by:   jerrychir
 * @Last Modified time: 2018-11-09 15:11:44
 */
package com.sangoes.boot.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TreeNode 树形节点
 *
 * @author jerrychir
 */
@Data
public class TreeNode implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    /**
     * 父主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long parentId;

    /**
     * 子节点
     */
    protected List<TreeNode> children = null;

    /**
     * 添加
     *
     * @param node
     */
    public void add(TreeNode node) {
        if (null == children) {
            children = new ArrayList<TreeNode>();
        }
        children.add(node);
    }
}