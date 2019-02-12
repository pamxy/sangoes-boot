package com.sangoes.boot.uc.modules.msg.controller;


import com.sangoes.boot.common.aop.log.annotation.RecLog;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.msg.dto.MsgDto;
import com.sangoes.boot.uc.modules.msg.service.IMsgCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @RecLog("发送消息")
    @PostMapping("/send")
    @ApiOperation(value = "发送消息", notes = "发送消息返回OK")
    @ResponseBody
    public Result<String> sendMsg(@RequestBody @Validated MsgDto msgDto) {
        msgCenterService.sendMessage(msgDto);
        return Result.success("发送成功");
    }

}
