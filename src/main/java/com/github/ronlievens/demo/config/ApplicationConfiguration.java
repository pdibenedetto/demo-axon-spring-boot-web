package com.github.ronlievens.demo.config;

import com.github.ronlievens.demo.config.metrics.UndertowMetricsHandlerWrapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableAutoConfiguration
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // By default all origins and GET, HEAD and POST methods are allowed.
                .allowedMethods(HttpMethod.GET.toString(),
                                HttpMethod.HEAD.toString(),
                                HttpMethod.POST.toString(),
                                HttpMethod.PUT.toString(),
                                HttpMethod.PATCH.toString(),
                                HttpMethod.DELETE.toString()
                )
                .allowedOrigins("*");
    }

    @Bean
    UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer(final UndertowMetricsHandlerWrapper undertowMetricsHandlerWrapper) {
        return deploymentInfo -> deploymentInfo.addOuterHandlerChainWrapper(undertowMetricsHandlerWrapper);
    }
}

