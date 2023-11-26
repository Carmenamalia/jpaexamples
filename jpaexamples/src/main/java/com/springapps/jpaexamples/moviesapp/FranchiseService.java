package com.springapps.jpaexamples.moviesapp;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FranchiseService {
    FranchiseRepository franchiseRepository;
    MovieRepository movieRepository;

    @Autowired
    public FranchiseService(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    public Franchise addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Transactional
    public Franchise addMovieToFranchise(Long id, Movie movie) {
        Franchise franchise = franchiseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
        movie.setFranchise(franchise);
        franchise.getMovies().add(movie);
        return franchiseRepository.save(franchise);
    }



}
