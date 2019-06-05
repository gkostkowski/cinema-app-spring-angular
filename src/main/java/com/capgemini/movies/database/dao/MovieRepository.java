package com.capgemini.movies.database.dao;

import com.capgemini.movies.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findByTitle(String name);

    @Query("match (m:Movie)<-[r:SHOWS]-(s:Screening {entityId:{0}}) return m")
    Movie findByScreeningId(@Param("scr") long screeningId);

    List<Movie> findAll();

    @Query("match (g:Genre)<-[r1:HAS_GENRE]-(m:Movie)<-[r2:SHOWS]-(s:Screening) return m, g, s, r1, r2;")
    List<Movie> findAllFull();
}
