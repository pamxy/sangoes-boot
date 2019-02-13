package com.sangoes.boot.uc.modules.msg.controller;


import com.sangoes.boot.common.aop.common.CacheParam;
import com.sangoes.boot.common.aop.lock.annotation.CacheLock;
import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.aop.ratelimit.annotation.RateLimiter;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import com.sangoes.boot.uc.modules.msg.vo.MsgCountVo;
import com.sangoes.boot.uc.modules.msg.vo.MsgTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 消息中心 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2019-02-11
 */
@RestController
@RequestMapping("/api/msg/center")
@Api("消息管理类")
@Slf4j
public class MsgCenterController extends BaseController {

    private final IMsgCenterService msgCenterService;

    @Autowired
    public MsgCenterController(IMsgCenterService msgCenterService) {
        this.msgCenterService = msgCenterService;
    }

    /**
     * 发送消息
     *
     * @param msgDto
     * @return
     */
    @RateLimiter(prefix = "limiter:msg:center:send")
    @RecLog("发送消息")
    @PostMapping("/send")
    @ApiOperation(value = "发送消息", notes = "发送消息返回OK")
    @ResponseBody
    public Result<String> sendMsg(@RequestBody @Validated MsgDto msgDto) {
        msgCenterService.sendMessage(msgDto);
        return Result.success("发送成功");
    }

    /**
     * 消息分页(userId为空时查询全部)
     *
     * @param params
     * @return
     */
    @CacheLock(prefix = "lock:msg:center:page")
    @RateLimiter(prefix = "limiter:msg:center:page")
    @RecLog("消息分页")
    @GetMapping("/page")
    @ApiOperation(value = "消息分页", notes = "返回分页结果 (userId为空时查询全部)")
    @ResponseBody
    public Result<MsgTypeVo> pageMsg(@CacheParam @RequestParam Map<String, Object> params) {
        MsgTypeVo centers = msgCenterService.pageMsg(params);
        return Result.success(centers, "成功返回");
    }

    /**
     * 获取当前用户全部消息数量
     *
     * @return
     */
    @RateLimiter(prefix = "limiter:msg:center:count")
    @RecLog("获取当前用户全部消息数量")
    @GetMapping("/count")
    @ApiOperation(value = "获取当前用户全部消息数量", notes = "返回全部消息数量")
    @ResponseBody
    public Result<MsgCountVo> countMsg() {
        MsgCountVo counts = msgCenterService.countMsg();
        return Result.success(counts, "成功返回");
    }


}
