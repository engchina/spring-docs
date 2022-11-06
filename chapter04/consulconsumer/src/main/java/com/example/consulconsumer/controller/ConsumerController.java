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
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        URI uri = serviceInstance.getUri();
        String callService = new RestTemplate().getForObject(uri + "/hello", String.class);
        return callService;
    }
}
