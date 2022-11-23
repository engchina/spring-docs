package com.example.springbootopentracing.config;


import com.example.springbootopentracing.manager.CustomizedMDCScopeManager;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JaegerConfig {

    @Bean
    public TracerBuilderCustomizer tracerBuilderCustomizer() {
        log.info("### in JaegerConfig.tracerBuilderCustomizer() ###");
        return builder -> {
//            builder.withScopeManager(new MDCScopeManager.Builder().build());
            builder.withScopeManager(new CustomizedMDCScopeManager.Builder().build());
        };
    }
}
