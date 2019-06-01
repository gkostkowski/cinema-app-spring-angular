package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findByTitle(String name);

    List<Movie> findAll();

    @Query("match (g:Genre)<-[r1:HAS_GENRE]-(m:Movie)<-[r2:SHOWS]-(s:Screening) return m, g, s, r1, r2;")
    List<Movie> findAllFull();
}
