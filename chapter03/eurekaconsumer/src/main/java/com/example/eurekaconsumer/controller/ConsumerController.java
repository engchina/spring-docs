package com.example.eurekaconsumer.controller;

import com.example.eurekaconsumer.service.MyFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ConsumerController {

    @Resource
    MyFeignClient myFeignClient;

    @GetMapping("/hello")
    public String hello() {
        return myFeignClient.hello();
    }
}
