package com.sangoes.boot.uc.modules.msg.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sangoes.boot.common.entity.BaseEntity;
import com.sangoes.boot.uc.modules.msg.entity.enums.MsgTypeEnum;
import com.sangoes.boot.uc.modules.msg.entity.enums.SendTypeEnum;
import com.sangoes.boot.uc.modules.msg.entity.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息中心
 * </p>
 *
 * @author jerrychir
 * @since 2019-02-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MsgCenter对象", description = "消息中心")
public class MsgCenter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息类型 1消息 2通知 3待办 4手机推送")
    private Integer msgType;

    @ApiModelProperty(value = "消息分类标题")
    private String mainTitle;

    @ApiModelProperty(value = "消息标题")
    private String title;

    @ApiModelProperty(value = "消息副标题")
    private String subTitle;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息副图片")
    private String icon;

    @ApiModelProperty(value = "链接 跳转链接")
    private String url;

    @ApiModelProperty(value = "状态 0待办 1进行中 2处理中 3紧急")
    private Integer status;

    @ApiModelProperty(value = "读状态 0未读 1已读")
    private Integer readed;

    @ApiModelProperty(value = "发送类型 1定向 2群发 3广播")
    private Integer sendType;

    @ApiModelProperty(value = "发送者")
    private String sender;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "发送者主键")
    private Long senderId;

    @ApiModelProperty(value = "接收者")
    private String receiver;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "接收者主键")
    private Long receiverId;

    @ApiModelProperty(value = "铃音")
    private String sound;

    @ApiModelProperty(value = "备注")
    private String des;


}
