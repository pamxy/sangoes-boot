/*
 * @Author: jerrychir @sangoes
 * @Date: 2018-11-09 15:32:32
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-10 13:27:15
 */
package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;
import com.sangoes.boot.uc.modules.admin.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
public interface ISysMenuService extends IBaseService<SysMenu> {
    /**
     * 添加菜单
     *
     * @param menuDto
     * @return
     */
    Result<String> addMenu(MenuDto menuDto);

    /**
     * 获取菜单树形
     *
     * @return
     */
    Result<List<MenuTree>> getMenuTree();

    /**
     * 获取菜单列表
     *
     * @return
     */
    Result<List<SysMenu>> getMenuList();

    /**
     * 更加角色编码查询菜单
     *
     * @param roleCode
     * @return
     */
    List<MenuVo> listMenuByRoleCode(String roleCode);

    /**
     * 根据用户角色获取树形菜单
     *
     * @param roles
     * @return
     */
    List<MenuTree> getUserMenuTree(List<String> roles);

    /**
     * 更新菜单
     *
     * @param menuDto
     */
    void updateMenu(MenuDto menuDto);

    /**
     * 删除菜单
     *
     * @param menuDto
     */
    void deleteMenu(MenuDto menuDto);
}
