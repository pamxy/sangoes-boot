package com.sangoes.boot.uc.modules.admin.controller;

import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.OAuthDto;
import com.sangoes.boot.uc.modules.admin.entity.OauthClientDetails;
import com.sangoes.boot.uc.modules.admin.service.IOauthClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/19 3:24 PM
 */
@RestController
@RequestMapping("/admin/oauth")
@Api("授权管理类")
@Slf4j
public class OAuthController extends BaseController {


    @Autowired
    private IOauthClientDetailsService oauthService;

    /**
     * 添加授权
     *
     * @param oauthDto
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加授权", notes = "返回添加信息")
    @ResponseBody
    public Result<String> addOAuth(@RequestBody @Validated(OAuthDto.AddOAuthGroup.class) OAuthDto oauthDto) {
        oauthService.saveOAuth(oauthDto);
        return Result.success("添加成功");
    }

    /**
     * 授权分页
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "授权分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<OauthClientDetails>> getOAuthPage(@RequestParam Map<String, Object> params) {
        PageData<OauthClientDetails> result = oauthService.selectOAuthPage(params);
        return Result.success(result, "返回成功");
    }
}
