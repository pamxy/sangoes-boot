/*
 * @Author: jerrychir @sangoes
 * @Date: 2018-11-09 15:29:11
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-10 13:30:16
 */
package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.mapper.SysMenuMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;
import com.sangoes.boot.uc.modules.admin.vo.MenuVo;
import com.sangoes.boot.uc.utils.BuildTreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
//@CacheConfig(cacheNames = "common")
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

    /**
     * 获取菜单列表
     */
    @Override
    public Result<List<SysMenu>> getMenuList() {
        // 获取全部菜单
        List<SysMenu> list = this.list(new QueryWrapper<SysMenu>());
        return Result.success(list, "成功");
    }

    /**
     * 更加角色编码查询菜单和权限
     *
     * @param roleCode
     * @return
     */
    @Cacheable(value = "menu", key = "'permission:'+#roleCode")
    @Override
    public List<MenuVo> listMenuByRoleCode(String roleCode) {
        return baseMapper.findMenuAuthByRoleCode(roleCode);
    }

    /**
     * 根据用户角色获取树形菜单
     *
     * @param roles
     * @return
     */
    @Override
    public List<MenuTree> getUserMenuTree(List<String> roles) {
        // 获取全部菜单
        List<SysMenu> list = baseMapper.findMenuByUserRoleCode(roles);
//        List<SysMenu> list = this.list(new QueryWrapper<SysMenu>());
        // 变成树形
        List<MenuTree> menuTrees = BuildTreeUtil.buildMenuTree(list, -1L);
        return menuTrees;
    }
}
