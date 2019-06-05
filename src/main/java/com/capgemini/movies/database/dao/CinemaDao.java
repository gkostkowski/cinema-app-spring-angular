package com.capgemini.movies.database.dao;

import com.capgemini.movies.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CinemaDao")
public interface CinemaDao {
    List<com.capgemini.movies.domain.Movie> getMovies();

    Movie getMovieByTitle(String title);

    List<com.capgemini.movies.domain.Screening> getScreenings();

    List<com.capgemini.movies.domain.Screening> getScreeningsForMovie(com.capgemini.movies.domain.Movie movie);

    List<Screening> getScreeningsForMovieTitle(String title);

    List<com.capgemini.movies.domain.ScreeningRoom> getScreeningRooms();

    com.capgemini.movies.domain.Ticket getTicketById(String id);

    List<com.capgemini.movies.domain.Ticket> getAllTickets();

    List<com.capgemini.movies.domain.Genre> getGenres();

    List<com.capgemini.movies.domain.Seat> getSeats();

    List<Seat> getAvailablePlacesForScreening(com.capgemini.movies.domain.Screening screening);

    void addTicket(com.capgemini.movies.domain.Ticket ticket);

    com.capgemini.movies.domain.Screening getScreeningById(Long id);

    ScreeningRoom getScreeningRoomByScreeningId(long screeningId);

    Movie getMoviesByScreening(Long entityId);
}
