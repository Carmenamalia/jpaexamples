package com.springapps.jpaexamples.moviesapp;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}) //tabelul de legatura dintre movies si characters
    @JoinTable(name = "movie_character", //detine relatia de legatura dintre tabele
            joinColumns = @JoinColumn(name = "character_id"),//cheia straina care leaga character de movie_character- onetomany
            inverseJoinColumns = @JoinColumn(name = "movie_id")//cheia straina care leaga movie de movie_character -onetomany
    )
    private Set<Movie> movies;

    public Character(String name) {
        this.name = name;
    }

    public Character() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        if (movies == null){
            movies= new HashSet<>();
        }
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
