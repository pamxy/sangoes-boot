package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto.BindRoleGroup;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.utils.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@RestController
@RequestMapping("admin/user")
@Api("用户管理类")
@Slf4j
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
            @RequestBody @Validated({SignInDto.SignInMobileGroup.class}) SignInDto signInDto) {

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
            @RequestBody @Validated({SignInDto.SignInAccountGroup.class}) SignInDto signInDto) {

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

    /**
     * 用户分页
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "用户分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<SysUser>> getUserPage(@RequestParam Map<String, Object> params) {
        return userService.selectUserPage(params);
    }

    /**
     * 查询绑定角色
     *
     * @param id
     * @return
     */
    @GetMapping("/bind/role/info/{id}")
    @ApiOperation(value = "查询绑定角色", notes = "返回角色列表以及用户对应角色结果")
    @ResponseBody
    public Result<Map<String, Object>> infoBindRole(@PathVariable Long id) {

        return userService.infoBindRole(id);
    }

    /**
     * 绑定用户
     *
     * @param userDto
     * @return
     */
    @PostMapping("/bind/role")
    @ApiOperation(value = "绑定用户", notes = "返回绑定结果")
    @ResponseBody
    public Result<String> bindRole(@RequestBody @Validated({BindRoleGroup.class}) UserDto userDto) {

        return userService.bindRole(userDto);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息", notes = "返回用户信息结果")
    @ResponseBody
    public Result<SysUser> userInfo() {
        // 获取当前用户id
        Long userId = AuthUtils.getUserId();
        // 获取user
        SysUser user = userService.userInfo(userId);
        return Result.success(user, "成功");
    }
}
