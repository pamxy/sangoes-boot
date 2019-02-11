package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto.AddAuthGroup;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto.BatchDeleteAuthGroup;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto.DeleteAuthGroup;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/auth")
@Api("权限管理类")
public class SysAuthController extends BaseController {

    @Autowired
    private ISysAuthService authService;

    /**
     * 添加权限
     *
     * @param authDto
     * @return
     */
    @RecLog("添加权限")
    @PostMapping("/add")
    @ApiOperation(value = "添加权限", notes = "返回添加结果")
    @ResponseBody
    public Result<String> add(@RequestBody @Validated(AddAuthGroup.class) AuthDto authDto) {
        return authService.addAuth(authDto);
    }

    /**
     * 更新(修改)权限
     *
     * @param authDto
     * @return
     */
    @RecLog("更新(修改)权限")
    @PutMapping("/update")
    @ApiOperation(value = "更新(修改)权限", notes = "返回更新结果")
    @ResponseBody
    public Result<String> updateAuth(@RequestBody @Validated(AuthDto.UpdateAuthGroup.class) AuthDto authDto) {
        // 查询登录用户的角色名
        String userRoles = AuthUtils.getUserRoles();
        authDto.setRoleCode(userRoles);
        // 更新
        authService.updateAuth(authDto);
        return Result.success("更新成功");
    }

    /**
     * 删除权限
     *
     * @param authDto
     * @return
     */
    @RecLog("删除权限")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除权限", notes = "返回删除结果")
    @ResponseBody
    public Result<String> deleteAuth(@RequestBody @Validated(DeleteAuthGroup.class) AuthDto authDto) {
        // 查询登录用户的角色名
        String userRoles = AuthUtils.getUserRoles();
        authDto.setRoleCode(userRoles);
        // 删除
        authService.deleteAuth(authDto);
        return Result.success("删除成功");
    }

    /**
     * 批量删除权限
     *
     * @param authDto
     * @return
     */
    @RecLog("批量删除权限")
    @DeleteMapping("/batch/delete")
    @ApiOperation(value = "批量删除权限", notes = "返回删除结果")
    @ResponseBody
    public Result<String> batchDeleteAuth(@RequestBody @Validated(BatchDeleteAuthGroup.class) AuthDto authDto) {
        // 查询登录用户的角色名
        String userRoles = AuthUtils.getUserRoles();
        authDto.setRoleCode(userRoles);
        // 删除
        authService.batchDeleteAuth(authDto);
        return Result.success("删除成功");
    }

    /**
     * 权限分页
     *
     * @param params
     * @return
     */
    @RecLog("权限分页")
    @GetMapping("/page")
    @ApiOperation(value = "权限分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<SysAuth>> pageAuthByMenuId(@RequestParam Map<String, Object> params) {
        return authService.pageAuthByMenuId(params);
    }

}
