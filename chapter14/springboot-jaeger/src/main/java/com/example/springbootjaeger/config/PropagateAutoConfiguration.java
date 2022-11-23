package com.example.springbootjaeger.config;

import com.example.springbootjaeger.manager.CustomizedMDCScopeManager;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropagateAutoConfiguration {
    @Bean
    public TracerBuilderCustomizer tracerBuilderCustomizer() {
        return builder -> builder.withScopeManager(new CustomizedMDCScopeManager.Builder().build());
    }
}
