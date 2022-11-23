package com.example.springbootopentracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringbootOpentracingMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOpentracingMainApplication.class, args);
    }

}
