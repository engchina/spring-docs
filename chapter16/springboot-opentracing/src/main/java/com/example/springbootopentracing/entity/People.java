package com.example.springbootopentracing.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EntityScan("com.example.springbootopentracing.entity")
@Table(name = "people")
public class People {

    @Id
    private String name;

    @Column(nullable = false)
    private String title = "";

    @Column(nullable = false)
    private String description = "";

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
