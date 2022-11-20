package com.example.springbootopentracing.repository;

import com.example.springbootopentracing.entity.People;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories("com.example.springbootopentracing.repository")
public interface PeopleRepository extends CrudRepository<People, String> {
}
