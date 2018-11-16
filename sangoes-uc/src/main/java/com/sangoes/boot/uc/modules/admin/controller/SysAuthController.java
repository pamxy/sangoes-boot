package com.sangoes.boot.uc.modules.admin.controller;

import java.util.Map;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto.AddAuthGroup;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
 * 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
@RestController
@RequestMapping("api/admin/auth")
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
    @PostMapping("/add")
    @ApiOperation(value = "添加权限", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addRole(@RequestBody @Validated(AddAuthGroup.class) AuthDto authDto) {
        return authService.addAuth(authDto);
    }

    /**
     * 权限分页
     * 
     * @param userDto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "权限分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<SysAuth>> pageAuthByMenuId(@RequestParam Map<String, Object> params) {
        return authService.pageAuthByMenuId(params);
    }

}
