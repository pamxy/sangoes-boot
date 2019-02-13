package com.sangoes.boot.uc.modules.msg.dto;

import com.sangoes.boot.common.dto.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 消息分页Dto
 * @author jerrychir
 * @date 2019 2019/2/13 7:34 PM
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MsgPageDto extends PageDto {

    @ApiModelProperty(value = "用户主键 当用户主键为空")
    private Long userId;

    @ApiModelProperty(value = "消息类型 1消息 2通知 3待办 4手机推送")
    private Integer type;
}
