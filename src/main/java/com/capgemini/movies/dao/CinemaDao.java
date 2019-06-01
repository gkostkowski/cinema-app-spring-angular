package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CinemaDao")
public interface CinemaDao {
    List<com.capgemini.movies.database.domain.Movie> getMovies();

    Movie getMovieByTitle(String title);

    List<com.capgemini.movies.database.domain.Screening> getScreenings();

    List<com.capgemini.movies.database.domain.Screening> getScreeningsForMovie(com.capgemini.movies.database.domain.Movie movie);

    List<Screening> getScreeningsForMovieTitle(String title);

    List<com.capgemini.movies.database.domain.ScreeningRoom> getScreeningRooms();

    com.capgemini.movies.database.domain.Ticket getTicketById(long id);

    List<com.capgemini.movies.database.domain.Ticket> getAllTickets();

    List<com.capgemini.movies.database.domain.Genre> getGenres();

    List<com.capgemini.movies.database.domain.Seat> getSeats();

    List<Seat> getAvailablePlacesForScreening(com.capgemini.movies.database.domain.Screening screening);

    void addTicket(com.capgemini.movies.database.domain.Ticket ticket);

    com.capgemini.movies.database.domain.Screening getScreeningById(Long id);
}
