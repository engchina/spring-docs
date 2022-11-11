package com.example.consulconsumer.service;

import org.springframework.stereotype.Component;

@Component
public class MyFeignHystrixClient implements MyFeignClient {

    @Override
    public String hello(){
        return "Error by Hystrix!";
    }
}
