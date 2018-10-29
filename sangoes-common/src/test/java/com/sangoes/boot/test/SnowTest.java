package com.sangoes.boot.test;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/28 11:04 PM
 */
@Slf4j
public class SnowTest {
    @Test
    public void getSnowFalk(){
        Snowflake snowflake = new Snowflake(3L, 5L);
        log.info("ss:",snowflake.nextId());
        System.out.println(snowflake.nextId());
    }
}
