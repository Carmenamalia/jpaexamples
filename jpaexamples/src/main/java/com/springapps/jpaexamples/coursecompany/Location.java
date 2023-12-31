package com.springapps.jpaexamples.coursecompany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String street;
    private int number;
}
