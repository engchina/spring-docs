package com.example.eurekaconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-provider")
public interface MyFeignClient {

    @GetMapping("/hello")
    public String hello();
}
