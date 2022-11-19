package com.example.springbootopentracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.springbootopentracing.repository")
@EntityScan("com.example.springbootopentracing.entity")
@SpringBootApplication
public class SpringbootOpentracingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOpentracingApplication.class, args);
    }

}
