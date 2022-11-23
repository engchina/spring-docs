package com.example.springbootjaeger.controller;

import com.example.springbootjaeger.service.MainService;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @Resource
    MainService mainService;

    @GetMapping("/hello")
    public String sayHello() {
        String msg = mainService.sayHello();
        Method1();
        return msg;
    }

    private void Method1() {
        log.info(" ### in MainController.Method1() ### ");
        Method2();
    }

    private void Method2() {
        log.info(" ### in MainController.Method2() ### ");
    }
}
