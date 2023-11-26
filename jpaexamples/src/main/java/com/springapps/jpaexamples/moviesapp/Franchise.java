package com.springapps.jpaexamples.moviesapp;

import jakarta.persistence.*;


import java.util.List;


@Entity
public class Franchise {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String franchiseName;
    @OneToMany(mappedBy = "franchise",cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    private List<Movie> movies;

    public Franchise(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public Franchise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "id=" + id +
                ", franchiseName='" + franchiseName + '\'' +
                ", movies=" + movies +
                '}';
    }

}
