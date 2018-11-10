/*
 * @Author: jerrychir @sangoes 
 * @Date: 2018-11-09 15:21:21 
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-09 16:10:37
 */
package com.sangoes.boot.uc.utils;

import java.util.ArrayList;
import java.util.List;

import com.sangoes.boot.common.vo.TreeUtil;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;

import org.springframework.beans.BeanUtils;

/**
 * BuildTreeUtil 生成树形工具
 */
public class BuildTreeUtil {

    /**
     * 生成菜单树形
     * 
     * @param menus
     * @param root
     * @return
     */
    public static List<MenuTree> buildMenuTree(List<SysMenu> menus, Long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (SysMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setName(menu.getMenuName());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

}