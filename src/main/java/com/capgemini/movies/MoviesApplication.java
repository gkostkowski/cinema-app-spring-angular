package com.capgemini.movies;

import com.capgemini.movies.dao.ScreeningRepository;
import com.capgemini.movies.database.domain.Movie;
import com.capgemini.movies.dao.MovieRepository;
import com.capgemini.movies.database.domain.Screening;
import com.capgemini.movies.rest.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

//@EnableNeo4jRepositories("com.capgemini.movies.dao")
//@EnableNeo4jRepositories(basePackageClasses = com.capgemini.movies.dao.MovieRepository.class)
//@EnableNeo4jRepositories(basePackageClasses = com.capgemini.movies.dao.MovieRepository.class)
@SpringBootApplication
public class MoviesApplication {

	private final static Logger log = LoggerFactory.getLogger(MoviesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

//	@Bean
//	CommandLineRunner demo(@Autowired MovieRepository movieRepository,
//						   @Autowired ScreeningRepository screeningRepository) {
//		return args -> {
//
////			MovieTest matrix = movieRepository.findByTitleContaining("Matrix");
//			Movie m = movieRepository.findByTitle("The Matrix");
////			MovieTest m = testRepository.findByTitle("The Matrix");
//			System.out.println(m);
//			System.out.println(movieRepository.findAll());
//			System.out.println(screeningRepository.findScreeningByMovie_Title("The Matrix"));
//
//
//			log.info(String.format("Found movie: %s", m));
//		};
//	}

//	@Bean
//	CommandLineRunner demo(@Autowired CinemaService cinemaService) {
//		return args -> {
//
////			MovieTest matrix = movieRepository.findByTitleContaining("Matrix");
//			Map<Long, Screening> m = cinemaService.getScreeningsForMovieTitleMap("The Matrix");
////			MovieTest m = testRepository.findByTitle("The Matrix");
//			System.out.println(m);
//
//		};
//	}
}
