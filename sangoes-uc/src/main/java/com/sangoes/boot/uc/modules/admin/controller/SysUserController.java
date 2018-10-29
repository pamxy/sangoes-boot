package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signup")
    @ApiOperation(value = "根据手机号码注册", notes = "注册返回OK")
    public String signupByMobile(@RequestBody @Validated SignUpDto signUpDto) {
        //
        SysUser sysUser = new SysUser();
        //复制
        BeanUtils.copyProperties(signUpDto,sysUser);
        sysUser.setUsername("xxxx");
        userService.signUpByMobile(signUpDto);
        return "xxx";
    }

}
