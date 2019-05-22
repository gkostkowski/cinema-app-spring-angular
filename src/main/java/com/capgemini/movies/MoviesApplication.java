package com.capgemini.movies;

import com.capgemini.movies.dao.MovieRepository;
import com.capgemini.movies.database.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


import java.util.Arrays;
import java.util.List;

@EnableNeo4jRepositories
@SpringBootApplication
public class MoviesApplication {

	private final static Logger log = LoggerFactory.getLogger(MoviesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(MovieRepository movieRepository) {
		return args -> {

			Movie matrix = movieRepository.findByTitleContaining("Matrix");


			log.info("Found movie:", matrix);
		};
	}
}
