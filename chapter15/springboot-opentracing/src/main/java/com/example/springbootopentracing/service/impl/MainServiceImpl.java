package com.example.springbootopentracing.service.impl;

import com.example.springbootopentracing.annotation.TraceMethod;
import com.example.springbootopentracing.entity.People;
import com.example.springbootopentracing.repository.PeopleRepository;
import com.example.springbootopentracing.service.MainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MainServiceImpl implements MainService {

    @Resource
    PeopleRepository peopleRepository;
    @Override
    @TraceMethod
    public People getPeople(String name) {
        Optional<People> peopleOpt = peopleRepository.findById(name);
        if (peopleOpt.isPresent()) {
            return peopleOpt.get();
        }
        return new People(name);
    }

    @Override
    @TraceMethod
    public String formatGreeting(People people) {
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
