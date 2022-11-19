package com.example.springbootopentracing.controller;

import com.example.springbootopentracing.entity.People;
import com.example.springbootopentracing.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        People people = getPeople(name);
        String response = formatGreeting(people);
        return response;
    }

    private People getPeople(String name) {
        Optional<People> peopleOpt = peopleRepository.findById(name);
        if (peopleOpt.isPresent()) {
            return peopleOpt.get();
        }
        return new People(name);
    }

    private String formatGreeting(People people) {
        String response = "Hello, ";
        if (!people.getTitle().isEmpty()) {
            response += people.getTitle() + " ";
        }
        response += people.getName() + "!";
        if (!people.getDescription().isEmpty()) {
            response += " " + people.getDescription();
        }
        return response;
    }
}
