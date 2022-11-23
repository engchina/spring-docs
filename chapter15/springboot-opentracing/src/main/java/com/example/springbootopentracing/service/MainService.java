package com.example.springbootopentracing.service;

import com.example.springbootopentracing.entity.People;

public interface MainService {

    People getPeople(String name);

    String formatGreeting(People people);
}
