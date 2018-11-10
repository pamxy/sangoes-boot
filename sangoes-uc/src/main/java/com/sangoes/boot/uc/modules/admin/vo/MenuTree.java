/*
 * @Author: jerrychir 
 * @Date: 2018-11-09 15:05:44 
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-09 16:10:10
 */
package com.sangoes.boot.uc.modules.admin.vo;

import com.sangoes.boot.common.vo.TreeNode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MenuTree 菜单树形VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MenuTree extends TreeNode {
    // 菜单名
    private String name;
    // 菜单编码
    private String menuCode;
    // 菜单图标
    private String icon;
    // 菜单状态
    private Integer status;
    // 菜单描述
    private String des;

    public MenuTree() {

    }

}