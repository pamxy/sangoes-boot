package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@RestController
@RequestMapping("user")
@Api("用户管理类")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     * @return
     */
    @PostMapping("/signup")
    @ApiOperation(value = "根据手机号码注册", notes = "注册返回OK")
    @ResponseBody
    public Result<String> signupByMobile(@RequestBody @Validated SignUpDto signUpDto) {
        // 手机号码注册
        return userService.signUpByMobile(signUpDto);
    }

    /**
     * 根据手机号码登陆
     * 
     * @param signInDto
     * @return
     */
    @PostMapping("/signin/mobile")
    @ApiOperation(value = "根据手机号码登陆", notes = "登陆返回token")
    @ResponseBody
    public Result<String> signinByMobile(
            @RequestBody @Validated({ SignInDto.SignInMobileGroup.class }) SignInDto signInDto) {

        return userService.signinByMobile(signInDto);
    }

    /**
     * 根据用户名登陆
     * 
     * @param signInDto
     * @return
     */
    @PostMapping("/signin/account")
    @ApiOperation(value = "根据用户名登陆", notes = "登陆返回token")
    @ResponseBody
    public Result<String> signinByAccount(
            @RequestBody @Validated({ SignInDto.SignInAccountGroup.class }) SignInDto signInDto) {

        return userService.signinByAccount(signInDto);
    }

    /**
     * 添加用户
     * 
     * @param userDto
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addUser(@RequestBody @Validated UserDto userDto) {

        return userService.addUser(userDto);
    }
}
