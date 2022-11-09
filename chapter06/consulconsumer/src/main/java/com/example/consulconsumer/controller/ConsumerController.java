package com.example.consulconsumer.controller;

import com.example.consulconsumer.service.MyFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    MyFeignClient myFeignClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        // 这里的主机名就是注册Consul的服务的id,也就是service-provider
        String callService = myFeignClient.hello();
        return callService;
    }
}
