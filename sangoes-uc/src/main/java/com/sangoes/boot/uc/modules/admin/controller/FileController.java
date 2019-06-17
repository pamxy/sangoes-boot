package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.entity.File;
import com.sangoes.boot.uc.modules.admin.service.IFileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 文件管理/附件管理 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2019-06-14
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/file")
public class FileController extends BaseController {


    @Autowired
    private IFileService fileService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RecLog("上传文件")
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "返回上传结果")
    @ResponseBody
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("文件:{}",file);
        // 上传头像
        fileService.uploadFile(file);
        return Result.success("上传成功");
    }


    /**
     * 文件分页
     *
     * @param params
     * @return
     */
    @RecLog("文件分页")
    @GetMapping("/page")
    @ApiOperation(value = "文件分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<File>> getOAuthPage(@RequestParam Map<String, Object> params) {
        PageData<File> result = fileService.selectFilePage(params);
        return Result.success(result, "返回成功");
    }
}
