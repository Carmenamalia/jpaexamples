package com.springapps.jpaexamples.moviesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterService {
    CharacterRepository characterRepository;
    MovieRepository movieRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }
    public Character addCharacter(Character character){
        return characterRepository.save(character);
    }
    @Transactional //adaug un caracter intr-un film
    public Movie addCharacterToMovie(Long movieId,Character character) throws Exception {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()->new Exception("movie not found"));
        movie.getCharacters().add(character);//adaug caracter in lista de caractere a filmului
        character.getMovies().add(movie);//adaug filmul in lista de filme a caracterului
        return movieRepository.save(movie);//salvez filmul(se face cascadare)
    }
}
