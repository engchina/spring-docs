package com.example.consulconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(contextId = "feignClient", name = "service-provider", configuration = FeignConfig.class)
@FeignClient(name = "service-provider", fallback = MyFeignHystrixClient.class)
public interface MyFeignClient {
    //    @RequestLine("GET /hello")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}