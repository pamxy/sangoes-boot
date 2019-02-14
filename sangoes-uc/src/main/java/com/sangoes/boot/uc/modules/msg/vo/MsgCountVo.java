package com.sangoes.boot.uc.modules.msg.vo;

import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.vo.BaseVo;
import com.sangoes.boot.uc.modules.msg.entity.MsgCenter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * msg vo
 *
 * @author jerrychir
 * @date 2019 2019/2/13 2:34 PM
 */
@Accessors(chain = true)
@Data
public class MsgCountVo implements Serializable {

    /**
     * 已读数量
     */
    private Integer readCount;

    /**
     * 未读总数量
     */
    private Integer unreadCount;


}
