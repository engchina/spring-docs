package com.oracle.springboot.config;

import io.jaegertracing.internal.MDCScopeManager;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MDCConfig {

    @Bean
    public TracerBuilderCustomizer tracerBuilderCustomizer() {
        log.info("### in MDCConfig.tracerBuilderCustomizer() ###");
        return builder -> {
            builder.withScopeManager(new MDCScopeManager.Builder().build());
        };
    }
}