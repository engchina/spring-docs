package com.example.springbootlistener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping("/hello")
    public String sayHello(){
        log.info("### MainController.sayHello() ###");
        return "Hello Springboot Listener";
    }
}
