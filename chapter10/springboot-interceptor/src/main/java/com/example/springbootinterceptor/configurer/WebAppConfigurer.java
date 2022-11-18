package com.example.springbootinterceptor.configurer;

import com.example.springbootinterceptor.interceptor.FirstInterceptor;
import com.example.springbootinterceptor.interceptor.SecondInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author engchina
 */
@Configuration
@Slf4j
public class WebAppConfigurer implements WebMvcConfigurer {

    @Resource
    FirstInterceptor firstInterceptor;

    @Resource
    SecondInterceptor secondInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**").order(0);
        registry.addInterceptor(secondInterceptor).addPathPatterns("/**").order(1);
    }
}
