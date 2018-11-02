package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
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
    public Result signupByMobile(@RequestBody @Validated SignUpDto signUpDto) {
        // 手机号码注册
        boolean saved = userService.signUpByMobile(signUpDto);
        if (saved) {
            return Result.success("注册成功");
        }
        return Result.failed("注册失败");
    }

}
