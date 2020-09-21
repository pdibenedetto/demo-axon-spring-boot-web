package com.github.ronlievens.demo.axon.web.config;

import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;

@Slf4j
@Configuration
@EnableAsync
@RequiredArgsConstructor
class ThreadPoolTaskExecutorConfiguration {

    public static final String THREAD_POOL_TASK_EXECUTOR_NAME = "taskExecutor";

    private final ThreadPoolTaskExecutorProperties properties;

    @Bean(name = THREAD_POOL_TASK_EXECUTOR_NAME)
    ThreadPoolTaskExecutor taskExecutor() {
        val executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix("ideal-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler((task, exec) -> log.error("ideal task rejected: " + task.toString()));
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    ExecutorServiceMetrics executorServiceMetrics(ThreadPoolTaskExecutor applicationTaskExecutor) {
        return new ExecutorServiceMetrics(applicationTaskExecutor.getThreadPoolExecutor(),
                                          THREAD_POOL_TASK_EXECUTOR_NAME,
                                          Collections.emptyList());
    }
}
