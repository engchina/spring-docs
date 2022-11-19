package com.example.springbootopentracing.controller;

import com.example.springbootopentracing.entity.People;
import com.example.springbootopentracing.repository.PeopleRepository;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class MainController extends TracedController {

    @Resource
    Tracer tracer;

    @Resource
    RestTemplate restTemplate;

    @Resource
    PeopleRepository peopleRepository;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        Span span = tracer.buildSpan("say-hello").start();
        try (Scope s = tracer.scopeManager().activate(span)) {
            People people = getPeople(name);
//            People people = getPeople(name, span);
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("name", people.getName());
            fields.put("title", people.getTitle());
            fields.put("description", people.getDescription());
            span.log(fields);
            String response = formatGreeting(people);
//            String response = formatGreeting(people, span);
            span.setTag("response", response);
            return response;
        } finally {
            span.finish();
        }
    }

    private People getPeople(String name) {
//    private People getPeople(String name, Span parentSpan) {
        Span span = tracer.buildSpan("get-people").start();
//        Span span = tracer.buildSpan("get-people").asChildOf(parentSpan).start();
        try (Scope s = tracer.scopeManager().activate(span)) {
            Optional<People> peopleOpt = peopleRepository.findById(name);
            if (peopleOpt.isPresent()) {
                return peopleOpt.get();
            }
            return new People(name);
        } finally {
            span.finish();
        }
    }

    private String formatGreeting(People people) {
//    private String formatGreeting(People people, Span parentSpan) {
        Span span = tracer.buildSpan("format-greeting").start();
//        Span span = tracer.buildSpan("format-greeting").asChildOf(parentSpan).start();
        try (Scope s = tracer.scopeManager().activate(span)) {
            String response = "Hello, ";
            if (!people.getTitle().isEmpty()) {
                response += people.getTitle() + " ";
            }
            response += people.getName() + "!";
            if (!people.getDescription().isEmpty()) {
                response += " " + people.getDescription();
            }
            return response;
        } finally {
            span.finish();
        }
    }

    @GetMapping("/sayHelloRest/{name}")
    public String sayHelloRest(@PathVariable String name, HttpServletRequest request) {
        Span span = startServerSpan("/sayHelloRest", request);
        try (Scope s = tracer.scopeManager().activate(span)) {
            People people = getPeopleRest(name);
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("name", people.getName());
            fields.put("title", people.getTitle());
            fields.put("description", people.getDescription());
            span.log(fields);
            String response = formatGreetingRest(people);
            span.setTag("response", response);
            return response;
        } finally {
            span.finish();
        }
    }

    private People getPeopleRest(String name) {
        String url = "http://localhost:8082/getPeople/" + name;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build(Collections.emptyMap());
        return get("get-people", uri, People.class, restTemplate);
    }

    private String formatGreetingRest(People people) {
        URI uri = UriComponentsBuilder //
                .fromHttpUrl("http://localhost:8081/formatGreeting") //
                .queryParam("name", people.getName()) //
                .queryParam("title", people.getTitle()) //
                .queryParam("description", people.getDescription()) //
                .build(Collections.emptyMap());
        return get("format-greeting", uri, String.class, restTemplate);
    }
}
