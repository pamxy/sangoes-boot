package com.sangoes.boot.uc.modules.msg.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sangoes.boot.uc.modules.msg.entity.enums.MsgTypeEnum;
import com.sangoes.boot.uc.modules.msg.entity.enums.SendTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 发送消息
 *
 * @author jerrychir
 * @date 2019 2019/2/11 10:43 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "发送消息对象", description = "发送消息对象")
public class MsgDto {

    /**
     * 发送消息组
     */
    public interface SendMsgGroup {

    }

    /**
     * 发送更新组
     */
    public interface SendUpdateGroup {

    }

    /**
     * 接收者
     */
//    @NotNull(message = "接收者不能为空", groups = {SendMsgGroup.class})
//    @ApiModelProperty(value = "接收者")
//    private List<String> receivers;

    /**
     * 接收者主键
     */
//    @NotNull(message = "接收者主键不能为空", groups = {SendMsgGroup.class})
//    @ApiModelProperty(value = "接收者主键")
//    private List<Long> receiverIds;

    @NotNull(message = "消息主键不能为空", groups = {SendUpdateGroup.class})
    @ApiModelProperty(value = "消息主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 消息类型
     */
    @NotNull(message = "消息类型不能为空", groups = {SendMsgGroup.class})
    @ApiModelProperty(value = "消息类型 1消息 2通知 3待办 4手机推送")
    private Integer msgType;

    /**
     * 消息分类标题
     */
    @NotBlank(message = "消息分类标题不能为空", groups = {SendMsgGroup.class})
    @ApiModelProperty(value = "消息分类标题")
    private String mainTitle;

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空", groups = {SendMsgGroup.class})
    @ApiModelProperty(value = "消息标题")
    private String title;

    /**
     * 消息副标题
     */
    @ApiModelProperty(value = "消息副标题")
    private String subTitle;

    /**
     * 内容
     */
    @ApiModelProperty(value = "消息内容")
    private String content;

    /**
     * 发送类型
     */
    @NotNull(message = "发送类型不能为空", groups = {SendMsgGroup.class})
    @ApiModelProperty(value = "发送类型 1定向 2群发 3广播")
    private Integer sendType;

    /**
     * 跳转链接
     */
    @ApiModelProperty(value = "链接 跳转链接")
    private String url;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private List<String> roleCode;

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private List<String> userId;


    @ApiModelProperty(value = "读状态 0未读 1已读")
    private Integer readed;
}
