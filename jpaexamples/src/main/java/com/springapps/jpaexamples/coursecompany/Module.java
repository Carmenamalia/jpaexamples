package com.springapps.jpaexamples.coursecompany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Module {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
