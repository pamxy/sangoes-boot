/*
 * @Author: jerrychir @sangoes
 * @Date: 2018-11-09 15:21:21
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-09 16:10:37
 */
package com.sangoes.boot.uc.utils;

import com.sangoes.boot.common.vo.TreeUtil;
import com.sangoes.boot.uc.modules.admin.entity.Depart;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;
import com.sangoes.boot.uc.modules.admin.vo.DictTree;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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
        return TreeUtil.buildByRecursive(trees, root);
    }


    /**
     * 生成部门树形
     *
     * @param departs
     * @param root
     * @return
     */
    public static List<DepartTree> buildDepartTree(List<Depart> departs, Long root) {
        List<DepartTree> trees = new ArrayList<DepartTree>();
        DepartTree node = null;
        for (Depart depart : departs) {
            node = new DepartTree();
            BeanUtils.copyProperties(depart, node);
            node.setName(depart.getDepartName());
            trees.add(node);
        }
        return TreeUtil.buildByRecursive(trees, root);
    }

    /**
     * 生成字典树形
     *
     * @param dicts
     * @param root
     * @return
     */
    public static List<DictTree> buildDictTree(List<Dict> dicts, Long root) {
        List<DictTree> trees = new ArrayList<DictTree>();
        DictTree node = null;
        for (Dict dict : dicts) {
            node = new DictTree();
            BeanUtils.copyProperties(dict, node);
            node.setName(dict.getDictValue());
            node.setKey(dict.getDictKey());
            node.setValue(dict.getDictValue());
            trees.add(node);
        }
        return TreeUtil.buildByRecursive(trees, root);
    }

}