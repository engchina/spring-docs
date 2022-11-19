package com.example.springbootopentracing;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringbootOpentracingConsumerApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "8082");
        SpringApplication.run(SpringbootOpentracingConsumerApplication.class, args);
    }

}
