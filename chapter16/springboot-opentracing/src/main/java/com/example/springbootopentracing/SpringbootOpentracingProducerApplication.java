package com.example.springbootopentracing;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootOpentracingProducerApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "8081");
        SpringApplication.run(SpringbootOpentracingProducerApplication.class, args);
    }

}
