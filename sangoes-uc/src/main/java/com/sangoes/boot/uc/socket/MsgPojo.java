package com.sangoes.boot.uc.socket;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/1/31 5:32 PM
 */
@Data
public class MsgPojo implements Serializable {
    private String name;
    private Long id;
}
