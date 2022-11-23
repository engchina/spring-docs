package com.example.springbootjaeger.service.impl;

import com.example.springbootjaeger.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainServiceImpl implements MainService {
    @Override
    public String sayHello() {
        log.info("### in MainServiceImpl.sayHello() ###");
        return "Hello Jaeger";
    }
}
