package com.sangoes.boot.uc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/2/2 4:02 PM
 */
@Configuration
public class AsyncConfig {
    /**
     * web 消息socket
     *
     * @return
     */
    @Bean("webMsgTaskExecutor")
    public Executor webMsgTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(8);
        // 缓冲执行任务的队列
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        //当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("web-msg-socket-thread-");
        return taskExecutor;
    }

    /**
     * task线程
     *
     * @return
     */
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(8);
        // 缓冲执行任务的队列
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        //当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("task-thread-");
        return taskExecutor;
    }
}
