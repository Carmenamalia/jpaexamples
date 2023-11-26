package com.springapps.jpaexamples.moviesapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    CharacterService characterService;
    MovieService movieService;
    FranchiseService franchiseService;

    public Runner(CharacterService characterService, MovieService movieService,FranchiseService franchiseService) {
        this.characterService = characterService;
        this.movieService = movieService;
        this.franchiseService = franchiseService;
    }

    @Override
    public void run(String... args) throws Exception {
        Franchise franchise = new Franchise("DC");
        Character character = new Character("Jocker");
        Movie movie = movieService.addMovie(new Movie("Batman"));
        characterService.addCharacterToMovie(movie.getMovieId(),character);
        franchiseService.addFranchise(franchise);


    }
}
