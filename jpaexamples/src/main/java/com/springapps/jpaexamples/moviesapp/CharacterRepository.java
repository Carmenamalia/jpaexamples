package com.springapps.jpaexamples.moviesapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {

public List<Character> findAllByMoviesContaining(Movie movie);
}
