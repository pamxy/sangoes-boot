package com.sangoes.boot.uc.modules.admin.controller;

import java.util.Map;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto.AddRoleGroup;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto.BindMenu;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import com.sangoes.boot.uc.modules.admin.service.ISysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
@RestController
@RequestMapping("role")
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
    @PostMapping("/add")
    @ApiOperation(value = "添加角色", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addRole(@RequestBody @Validated(AddRoleGroup.class) RoleDto roleDto) {
        return roleService.addRole(roleDto);
    }

    /**
     * 用户分页
     * 
     * @param userDto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "用户分页", notes = "返回分页结果")
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
    @GetMapping("/bind/auth/info/{roleId}/{menuId}")
    @ApiOperation(value = "查询绑定权限", notes = "返回权限结果")
    @ResponseBody
    public Result<Map<String, Object>> infoBindAuth(@PathVariable Long roleId, @PathVariable Long menuId) {

        return roleService.infoBindAuth(roleId, menuId);
    }

    /**
     * 绑定菜单权限
     */
    @PostMapping("/bind/menu")
    @ApiOperation(value = "绑定菜单权限", notes = "返回绑定结果")
    @ResponseBody
    public Result<String> bindMenuAuth(@RequestBody @Validated({ BindMenu.class }) RoleDto roleDto) {
        roleService.bindMenuAuth(roleDto);
        return Result.success("绑定成功");
    }

}
