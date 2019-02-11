package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.security.token.AuthenticationsToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/2/2 8:21 PM
 */
@RestController
@Api("登录管理")
@RequestMapping("/api/login")
@Slf4j
public class LoginController extends BaseController {

    @GetMapping("/web")
    @ApiOperation(value = "添加父字典", notes = "返回添加信息")
    @ResponseBody
    public Result<String> webLogin() {
        log.info("login xxxxxxx");
//        new AuthenticationsToken();
        new WebAuthenticationDetailsSource();
;
        return Result.success("添加成功");
    }
}
