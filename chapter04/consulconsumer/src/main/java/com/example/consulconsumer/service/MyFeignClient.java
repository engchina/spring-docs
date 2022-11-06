package com.example.consulconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client")
public interface MyFeignClient {

    @GetMapping("/hello")
    public String hello();
}
