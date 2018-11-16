/*
 * @Author: jerrychir @sangoes 
 * @Date: 2018-11-09 15:41:43 
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-10 13:27:48
 */
package com.sangoes.boot.uc.modules.admin.controller;

import java.util.List;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto.AddMenuGroup;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;
import com.sangoes.boot.uc.modules.admin.vo.MenuTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
@RestController
@RequestMapping("api/admin/menu")
@Api("菜单管理类")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 添加菜单
     * 
     * @param roleDto
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加菜单", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addRole(@RequestBody @Validated(AddMenuGroup.class) MenuDto menuDto) {
        return menuService.addMenu(menuDto);
    }

    /**
     * 获取菜单树形结果
     * 
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单树形结果", notes = "返回树形结果")
    @ResponseBody
    public Result<List<MenuTree>> getMenuTree() {
        return menuService.getMenuTree();
    }

    /**
     * 获取菜单列表
     * 
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取菜单列表", notes = "返回菜单列表结果")
    @ResponseBody
    public Result<List<SysMenu>> getMenuList() {
        return menuService.getMenuList();
    }
}
