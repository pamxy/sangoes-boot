package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/19 3:24 PM
 */
@RestController
@RequestMapping("oauth")
@Api("OAUTH管理类")
@Slf4j
public class OAuthController extends BaseController {

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @DeleteMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "返回退出登录信息")
    @ResponseBody
    public Result<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // FIXME: 注销登录
        // 退出
        new SecurityContextLogoutHandler().logout(request, response, null);
        return Result.success("注销成功");
    }
}
