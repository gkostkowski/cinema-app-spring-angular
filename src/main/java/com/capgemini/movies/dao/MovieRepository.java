package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.Movie;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAllBy();

    Movie findByTitleContaining(String title);
}
