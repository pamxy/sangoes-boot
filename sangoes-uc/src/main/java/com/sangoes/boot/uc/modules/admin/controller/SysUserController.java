package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/signup")
    public String signup(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("xxxx");
        userService.save(sysUser);
        return "xxx";
    }

}
