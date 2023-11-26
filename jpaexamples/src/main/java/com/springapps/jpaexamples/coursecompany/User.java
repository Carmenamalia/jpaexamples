package com.springapps.jpaexamples.coursecompany;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    List<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
