package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.core.componet.AliyunOSSUploader;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto.BindRoleGroup;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
@RequestMapping("/api/admin/user")
@Api("用户管理类")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;


    @Autowired
    private AliyunOSSUploader aliyunOSSUploader;


    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     * @return
     */
    @RecLog("注册")
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
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @RecLog("退出登录")
    @DeleteMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "返回退出登录信息")
    @ResponseBody
    public Result<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // FIXME: 注销登录
        // 退出
        new SecurityContextLogoutHandler().logout(request, response, null);
        return Result.success("注销成功");
    }

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    @RecLog("添加用户")
    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "返回添加结果")
    @ResponseBody
    public Result<String> addUser(@RequestBody @Validated(UserDto.AddUserGroup.class) UserDto userDto) {
        userService.addUser(userDto);
        return Result.success("添加成功");
    }

    /**
     * 删除用户
     *
     * @param userDto
     * @return
     */
    @RecLog("删除用户")
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户", notes = "返回删除结果")
    @ResponseBody
    public Result<String> deleteUser(@RequestBody @Validated({UserDto.DeleteUserGroup.class}) UserDto userDto) {
        // 删除用户
        userService.deleteUser(userDto);
        return Result.success("删除成功");
    }

    /**
     * 批量删除用户
     *
     * @param userDto
     * @return
     */
    @RecLog("批量删除用户")
    @DeleteMapping("/batch/delete")
    @ApiOperation(value = "批量删除用户", notes = "返回删除结果")
    @ResponseBody
    public Result<String> batchDeleteUser(@RequestBody @Validated({UserDto.BatchDeleteUserGroup.class}) UserDto userDto) {
        // 删除用户
        userService.batchDeleteUser(userDto);
        return Result.success("删除成功");
    }

    /**
     * 更新(修改)用户
     *
     * @param userDto
     * @return
     */
    @RecLog("更新(修改)用户")
    @PutMapping("/update")
    @ApiOperation(value = "更新(修改)用户", notes = "返回更新结果")
    @ResponseBody
    public Result<String> updateUser(@RequestBody @Validated({UserDto.UpdateUserGroup.class}) UserDto userDto) {
        // 更新
        userService.updateUser(userDto);
        return Result.success("更新成功");
    }


    /**
     * 用户分页
     *
     * @param params
     * @return
     */
    @RecLog("用户分页")
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
    @RecLog("查询绑定角色")
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
    @RecLog("绑定用户")
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
    @RecLog("获取当前用户信息")
    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息", notes = "返回用户信息结果")
    @ResponseBody
    public Result<SysUser> userInfo() {
        // 获取当前用户id
        Long userId = AuthUtils.getUserId();
        // 获取user
        SysUser user = userService.userInfo(userId);
        // 获取当前角色
        List<String> roles = AuthUtils.getListUserRoles();
        roles.forEach(role->{
            log.info("role:{}",role);
        });
        return Result.success(user, "成功");
    }

    /**
     * 用户上传头像
     *
     * @param file
     * @return
     */
    @RecLog("用户上传头像")
    @PostMapping("/upload/avatar")
    @ApiOperation(value = "用户上传头像", notes = "返回头像结果")
    @ResponseBody
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        // 用户id
        Long userId = AuthUtils.getUserId();
        // 上传头像
        String imgUrl = userService.uploadAvatar(userId, file);
        return Result.success(imgUrl, "成功");
    }

    /**
     * 查询绑定部门树形
     *
     * @param id
     * @return
     */
    @RecLog("查询绑定部门树形")
    @GetMapping("/bind/depart/{id}")
    @ApiOperation(value = "查询绑定部门树形", notes = "返回绑定树形结果")
    @ResponseBody
    public Result<Map<String, Object>> infoBindDepartTree(@PathVariable Long id) {
        Map<String, Object> results = userService.infoBindDepartTree(id);
        return Result.success(results, "成功");
    }

    /**
     * 绑定部门
     *
     * @param userDto
     * @return
     */
    @RecLog("绑定部门")
    @PostMapping("/bind/depart")
    @ApiOperation(value = "绑定部门", notes = "返回绑定结果")
    @ResponseBody
    public Result<String> bindDepart(@RequestBody @Validated({UserDto.BindDepartGroup.class}) UserDto userDto) {
        userService.bindDepart(userDto);
        return Result.success("绑定成功");
    }
}
