/*
 * @Author: jerrychir 
 * @Date: 2018-11-09 15:11:44 
 * @Last Modified by:   jerrychir 
 * @Last Modified time: 2018-11-09 15:11:44 
 */
package com.sangoes.boot.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

/**
 * TreeNode 树形节点
 */
@Data
public class TreeNode {
    // 主键
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    // 父主键
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long parentId;

    // 子节点
    protected List<TreeNode> children = null;

    // 添加
    public void add(TreeNode node) {
        if (null == children) {
            children = new ArrayList<TreeNode>();
        }
        children.add(node);
    }
}