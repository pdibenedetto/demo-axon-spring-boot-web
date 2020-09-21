package com.github.ronlievens.demo.axon.web.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("async")
@RequiredArgsConstructor
public class ThreadPoolTaskExecutorProperties {
    private Integer maxPoolSize;
    private Integer corePoolSize;
    private Integer queueCapacity;
}
