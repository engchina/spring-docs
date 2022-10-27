package com.example.springbootdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.bmc.http.client.Serialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
//        return RestClientFactory.getObjectMapper();
        return Serialization.getObjectMapper();
    }
}
