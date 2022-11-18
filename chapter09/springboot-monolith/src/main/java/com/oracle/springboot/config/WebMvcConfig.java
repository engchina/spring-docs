package com.oracle.springboot.config;

import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;


/**
 * @author engchina
 */
@Configuration
public class WebMvcConfig{
    @Bean
    @ConditionalOnMissingBean(TracingFilter.class)
    public WebMvcConfigurerAdapter tracingHandlerInterceptor(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new TracingHandlerInterceptor(tracer,
                        Arrays.asList(HandlerInterceptorSpanDecorator.STANDARD_LOGS,
                                HandlerInterceptorSpanDecorator.HANDLER_METHOD_OPERATION_NAME)));
                super.addInterceptors(registry);
            }
        };
    }
}
