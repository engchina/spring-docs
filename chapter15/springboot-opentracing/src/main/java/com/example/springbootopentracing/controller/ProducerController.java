package com.example.springbootopentracing.controller;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProducerController {

    @Resource
    Tracer tracer;

    @GetMapping("/formatGreeting")
    public String formatGreeting(@RequestParam String name, @RequestParam String title,
                                 @RequestParam String description) {
        Span span = tracer.buildSpan("/formatGreeting").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            String greeting = span.getBaggageItem("greeting");
            if (greeting == null) {
                greeting = "Hello";
            }
            String response = greeting + ", ";
            if (!title.isEmpty()) {
                response += title + " ";
            }
            response += name + "!";
            if (!description.isEmpty()) {
                response += " " + description;
            }
            return response;
        } finally {
            span.finish();
        }
    }
}
