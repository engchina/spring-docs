package com.example.springbootopentracing.controller;

import com.example.springbootopentracing.entity.People;
import com.example.springbootopentracing.repository.PeopleRepository;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ConsumerController {

    @Resource
    Tracer tracer;
    
    @Resource
    PeopleRepository peopleRepository;

    @GetMapping("/getPeople/{name}")
    public People getPeople(@PathVariable String name) {
        Span span = tracer.buildSpan("/getPeople").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            People people = loadPeople(name);
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("name", people.getName());
            fields.put("title", people.getTitle());
            fields.put("description", people.getDescription());
            span.log(fields);
            return people;
        } finally {
            span.finish();
        }
    }

    private People loadPeople(String name) {
        Span span = tracer.buildSpan("get-people").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            Optional<People> peopleOpt = peopleRepository.findById(name);
            if (peopleOpt.isPresent()) {
                return peopleOpt.get();
            }
            return new People(name);
        } finally {
            span.finish();
        }
    }
}
