package com.example.consulconsumer.config;

//import feign.Contract;
//import org.springframework.context.annotation.Bean;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    @Bean
    Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }
}
