package com.example.springbootopentracing.controller;

import com.example.springbootopentracing.entity.People;
import com.example.springbootopentracing.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collections;

@RestController
@Slf4j
public class MainController extends TracedController {

    @Resource
    RestTemplate restTemplate;

    @Resource
    MainService mainService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        People people = mainService.getPeople(name);
        String response = mainService.formatGreeting(people);
        return response;
    }

    @GetMapping("/sayHelloRest/{name}")
    public String sayHelloRest(@PathVariable String name, HttpServletRequest request) {
        People people = getPeopleRest(name);
        String response = formatGreetingRest(people);
        return response;
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
