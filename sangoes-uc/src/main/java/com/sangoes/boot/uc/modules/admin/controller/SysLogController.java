package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.entity.SysLog;
import com.sangoes.boot.uc.modules.admin.service.ISysLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 日志 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2019-01-12
 */
@RestController
@RequestMapping("/api/admin/log")
public class SysLogController extends BaseController {

    @Autowired
    private ISysLogService logService;


    /**
     * 日志分页
     *
     * @param params
     * @return
     */
    @RecLog("日志分页")
    @GetMapping("/page")
    @ApiOperation(value = "日志分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<SysLog>> getUserPage(@RequestParam Map<String, Object> params) {
        PageData<SysLog> logs = logService.pageLog(params);
        return Result.success(logs, "成功");
    }

}
