package com.sangoes.boot.uc.modules.msg.controller;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.sangoes.boot.common.aop.elastic.annotation.Schedule;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/2/26 10:26 PM
 */
@Slf4j
//@Schedule(cron = "0/1 * * * * ?", eventTraceRdbDataSource = "dataSource")
public class JobService implements SimpleJob {
    /**
     * 执行作业.
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        log.error("ddddd:{}", "dsafdsafdsafdsafes");
    }
}
