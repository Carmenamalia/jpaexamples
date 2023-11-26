package com.springapps.jpaexamples.moviesapp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    MovieRepository movieRepository;
    FranchiseRepository franchiseRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, FranchiseRepository franchiseRepository) {
        this.movieRepository = movieRepository;
        this.franchiseRepository = franchiseRepository;
    }

    //adaug un film intr-o franciza.
//Vad toate filmele dintr-o franciza
//Vad toata caracterele dintr-un film
    @Transactional
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }


}
