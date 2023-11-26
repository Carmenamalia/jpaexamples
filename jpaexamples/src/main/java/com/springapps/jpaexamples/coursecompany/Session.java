package com.springapps.jpaexamples.coursecompany;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "session", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Attendance> attendances;
}
