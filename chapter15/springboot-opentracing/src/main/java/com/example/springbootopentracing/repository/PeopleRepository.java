package com.example.springbootopentracing.repository;

import com.example.springbootopentracing.entity.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<People, String> {
}
