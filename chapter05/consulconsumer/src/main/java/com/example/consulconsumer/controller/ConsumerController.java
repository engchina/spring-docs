package com.example.consulconsumer.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;


@RestController
public class ConsumerController {

    @Resource
    RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello() {
        // 这里的主机名就是注册Consul的服务的id,也就是service-provider
        String callService = restTemplate.getForObject("http://service-provider/hello", String.class);
        return callService;
    }
}
