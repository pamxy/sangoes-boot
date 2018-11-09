/*
 * @Author: jerrychir @sangoes 
 * @Date: 2018-11-09 15:29:11 
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-09 15:42:18
 */
package com.sangoes.boot.uc.modules.admin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.mapper.SysMenuMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;
import com.sangoes.boot.uc.utils.BuildTreeUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    /**
     * 添加菜单
     */
    @Override
    public Result<String> addMenu(MenuDto menuDto) {
        // 判断menuCode是否重复
        SysMenu menuDB = this
                .getOne(new QueryWrapper<SysMenu>().lambda().eq(SysMenu::getMenuCode, menuDto.getMenuCode()));
        if (ObjectUtil.isNotNull(menuDB)) {
            throw new HandleErrorException("菜单编码已存在");
        }
        // 复制
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(menuDto, sysMenu);
        // 保存
        boolean save = this.save(sysMenu);
        if (!save) {
            throw new HandleErrorException("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 获取菜单树形
     */
    @Override
    public Result<List<MenuTree>> getMenuTree() {
        // 获取全部菜单
        List<SysMenu> list = this.list(new QueryWrapper<SysMenu>());
        // 变成树形
        List<MenuTree> menuTrees = BuildTreeUtil.buildMenuTree(list, -1L);
        return Result.success(menuTrees, "成功");
    }

}