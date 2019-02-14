package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.ArrayUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto;
import com.sangoes.boot.uc.modules.admin.entity.*;
import com.sangoes.boot.uc.modules.admin.mapper.SysRoleAuthMapper;
import com.sangoes.boot.uc.modules.admin.mapper.SysRoleMapper;
import com.sangoes.boot.uc.modules.admin.mapper.SysRoleMenuMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;
import com.sangoes.boot.uc.modules.admin.service.ISysRoleService;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;
import com.sangoes.boot.uc.utils.BuildTreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysRoleAuthMapper roleAuthMapper;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysAuthService authService;

    /**
     * 添加角色
     */
    @Override
    public Result<String> addRole(RoleDto roleDto) {
        // 判断roleCode是否重复
        SysRole roleDB = this
                .getOne(new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleCode, roleDto.getRoleCode()));
        if (ObjectUtil.isNotNull(roleDB)) {
            throw new HandleErrorException("角色编码已存在");
        }
        // 复杂
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        // 保存
        boolean save = this.save(sysRole);
        if (!save) {
            throw new HandleErrorException("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 分页角色
     */
    @Override
    public Result<PageData<SysRole>> selectRolePage(Map<String, Object> params) {
        PageData<SysRole> selectPage = this.selectPage(new PageQuery(params));
        return Result.success(selectPage, "成功");
    }

    /**
     * 查询绑定菜单权限
     */
    @Override
    public Result<Map<String, Object>> infoBindMenu(Long roleId) {
        // 判断id为空
        if (Validator.isNull(roleId)) {
            throw new HandleErrorException("查询id不能为空");
        }
        // 选择的菜单keys
        List<String> menuKeys = ArrayUtils.longListToStringList(roleMenuMapper.listMenuIdByRoleId(roleId));
        // 所有的菜单选项
        List<SysMenu> menus = menuService.list(new QueryWrapper<SysMenu>());
        // 变成树形
        List<MenuTree> menuTrees = BuildTreeUtil.buildMenuTree(menus, -1L);
        // 封装
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuKeys", menuKeys);
        map.put("menus", menuTrees);
        return Result.success(map, "成功");
    }

    /**
     * 查询绑定权限
     */
    @Override
    public Result<Map<String, Object>> infoBindAuth(Long roleId, Long menuId) {
        // 判断id为空
        if (Validator.isNull(menuId) && Validator.isNull(roleId)) {
            throw new HandleErrorException("查询id不能为空");
        }
        // 选中的权限keys
        List<String> authKeys = ArrayUtils.longListToStringList(roleAuthMapper.listAuthIdByRoleId(roleId, menuId));
        // 所有的权限选项
        // TODO 分页
        List<SysAuth> auths = authService.list(new QueryWrapper<SysAuth>().lambda().eq(SysAuth::getMenuId, menuId));
        // 封装
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authKeys", authKeys);
        map.put("auths", auths);
        return Result.success(map, "成功");
    }

    /**
     * 绑定菜单权限
     */
//    @CacheEvict(value = "auth", key = "'auth:roleCode:'+#roleDto.getRoleCode()")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindMenuAuth(RoleDto roleDto) {
        // roleId
        Long roleKey = roleDto.getRoleId();
        // menuId
        Long menuKey = roleDto.getMenuId();
        // 清空roleID对应的所有菜单
        roleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, roleKey));
        // 清空菜单对应的权限
        roleAuthMapper.delete(new QueryWrapper<SysRoleAuth>().lambda().eq(SysRoleAuth::getRoleId, roleKey)
                .eq(SysRoleAuth::getMenuId, menuKey));
        // 判断菜单id
        if (StrUtil.isNotBlank(roleDto.getMenuIds())) {
            String[] menuIds = roleDto.getMenuIds().split(",");
            for (String menuId : menuIds) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleKey);
                roleMenu.setMenuId(Long.parseLong(menuId));
                roleMenuMapper.insert(roleMenu);
            }
        }
        // 判断权限id
        if (StrUtil.isNotBlank(roleDto.getAuthIds())) {
            String[] authIds = roleDto.getAuthIds().split(",");
            for (String authId : authIds) {
                SysRoleAuth roleAuth = new SysRoleAuth();
                roleAuth.setRoleId(roleKey);
                roleAuth.setMenuId(menuKey);
                roleAuth.setAuthId(Long.parseLong(authId));
                roleAuthMapper.insert(roleAuth);
            }
        }
    }

    /**
     * 删除角色
     *
     * @param roleDto
     */
    @Override
    public void deleteRole(RoleDto roleDto) {
        // 查询role
        SysRole roleDB = this.getById(roleDto.getRoleId());
        if (ObjectUtil.isNull(roleDB)) {
            throw new HandleErrorException("角色被删除或不存在");
        }
        // 删除
        boolean flag = this.removeById(roleDto.getRoleId());
        if (!flag) {
            throw new HandleErrorException("删除失败");
        }
    }

    /**
     * 批量删除角色
     *
     * @param roleDto
     */
    @Override
    public void batchDeleteRole(RoleDto roleDto) {
        // 批量删除
        boolean flag = this.removeByIds(roleDto.getRoleIds());
        if (!flag) {
            throw new HandleErrorException("批量删除失败");
        }
    }

    /**
     * 更新角色
     *
     * @param roleDto
     */
    @Override
    public void updateRole(RoleDto roleDto) {
        // 查询
        SysRole roleDB = this.getById(roleDto.getId());
        if (ObjectUtil.isNull(roleDB)) {
            throw new HandleErrorException("角色为空或已删除");
        }
        // 新建
        SysRole role = new SysRole();
        BeanUtil.copyProperties(roleDto, role, CopyOptions.create().setIgnoreNullValue(true));
        // 更新
        boolean flag = this.updateById(role);
        if (!flag) {
            throw new HandleErrorException("更新失败");
        }
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<SysRole> getAllRoles() {
        List<SysRole> roles = this.list(new QueryWrapper<>());
        return roles;
    }
}
