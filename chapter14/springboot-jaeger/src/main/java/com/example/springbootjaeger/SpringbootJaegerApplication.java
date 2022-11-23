package com.example.springbootjaeger;

import com.example.springbootjaeger.config.PropagateJaegerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringbootJaegerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJaegerApplication.class, args);
    }

}
