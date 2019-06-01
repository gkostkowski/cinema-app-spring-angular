package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.Movie;
import com.capgemini.movies.database.domain.Screening;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Long> {

    List<Screening> findAll();

    List<Screening> findScreeningByMovie(Movie movie);

    List<Screening> findScreeningByMovie_Title(String title);
}
