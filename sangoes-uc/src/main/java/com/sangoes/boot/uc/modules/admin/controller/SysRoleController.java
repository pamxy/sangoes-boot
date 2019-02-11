package com.sangoes.boot.uc.modules.admin.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto.AddRoleGroup;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto.BindMenu;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import com.sangoes.boot.uc.modules.admin.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
@RestController
@RequestMapping("/api/admin/role")
@Api("角色管理类")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    /**
     * 添加角色
     *
     * @param roleDto
     * @return
     */
    @RecLog("添加角色")
    @PostMapping("/add")
    @ApiOperation(value = "添加角色", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addRole(@RequestBody @Validated(AddRoleGroup.class) RoleDto roleDto) {
        return roleService.addRole(roleDto);
    }

    /**
     * 删除权限
     *
     * @param roleDto
     * @return
     */
    @RecLog("删除权限")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除权限", notes = "返回删除结果")
    @ResponseBody
    public Result<String> deleteRole(@RequestBody @Validated({RoleDto.DeleteRoleGroup.class}) RoleDto roleDto) {
        // 删除权限
        roleService.deleteRole(roleDto);
        return Result.success("删除成功");
    }

    /**
     * 批量删除角色
     *
     * @param roleDto
     * @return
     */
    @RecLog("批量删除角色")
    @DeleteMapping("/batch/delete")
    @ApiOperation(value = "批量删除角色", notes = "返回删除结果")
    @ResponseBody
    public Result<String> batchDeleteRole(@RequestBody @Validated({RoleDto.BatchDeleteRoleGroup.class}) RoleDto roleDto) {
        // 删除角色
        roleService.batchDeleteRole(roleDto);
        return Result.success("删除成功");
    }

    /**
     * 更新(修改)角色
     *
     * @param roleDto
     * @return
     */
    @RecLog("更新(修改)角色")
    @PutMapping("/update")
    @ApiOperation(value = "更新(修改)角色", notes = "返回更新结果")
    @ResponseBody
    public Result<String> updateRole(@RequestBody @Validated({RoleDto.UpdateRoleGroup.class}) RoleDto roleDto) {
        // 更新
        roleService.updateRole(roleDto);
        return Result.success("更新成功");
    }

    /**
     * 角色分页
     *
     * @param params
     * @return
     */
    @RecLog("角色分页")
    @GetMapping("/page")
    @ApiOperation(value = "角色分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<SysRole>> getRolePage(@RequestParam Map<String, Object> params) {
        return roleService.selectRolePage(params);
    }

    /**
     * 查询绑定菜单
     *
     * @param roleId
     * @return
     */
    @RecLog("查询绑定菜单")
    @GetMapping("/bind/menu/info/{roleId}")
    @ApiOperation(value = "查询绑定菜单", notes = "返回菜单结果")
    @ResponseBody
    public Result<Map<String, Object>> infoBindMenu(@PathVariable Long roleId) {

        return roleService.infoBindMenu(roleId);
    }

    /**
     * 查询绑定权限
     *
     * @param roleId
     * @return
     */
    @RecLog("查询绑定权限")
    @GetMapping("/bind/auth/info/{roleId}/{menuId}")
    @ApiOperation(value = "查询绑定权限", notes = "返回权限结果")
    @ResponseBody
    public Result<Map<String, Object>> infoBindAuth(@PathVariable Long roleId, @PathVariable Long menuId) {

        return roleService.infoBindAuth(roleId, menuId);
    }

    /**
     * 绑定菜单权限
     */
    @RecLog("绑定菜单权限")
    @PostMapping("/bind/menu")
    @ApiOperation(value = "绑定菜单权限", notes = "返回绑定结果")
    @ResponseBody
    public Result<String> bindMenuAuth(@RequestBody @Validated({BindMenu.class}) RoleDto roleDto) {
        // 获取role
        SysRole role = roleService.getOne(new QueryWrapper<SysRole>().lambda().eq(SysRole::getId, roleDto.getRoleId()));
        if (ObjectUtil.isNull(role)) {
            throw new HandleErrorException("权限主键为空");
        }
        roleDto.setRoleCode(role.getRoleCode());
        // 绑定
        roleService.bindMenuAuth(roleDto);
        return Result.success("绑定成功");
    }

}
