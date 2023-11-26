package com.springapps.jpaexamples.moviesapp;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long movieId;
    @Column
    private String title;
    @ManyToOne
    @JoinColumn(name = "franchise_id")//cheia straina
    private Franchise franchise;//are o franciza,apartine unei francize
    @ManyToMany(mappedBy = "movies",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Character> characters;

    public Movie(String title) {
        this.title = title;
    }

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public Set<Character> getCharacters() {
        if (characters == null) {
            characters = new HashSet<>();
        }
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                '}';
    }
}
