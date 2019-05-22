package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CinemaDao")
public interface CinemaDao {
    List<Movie> getMovies();

    List<Screening> getScreenings();

    List<Screening> getScreeningsForMovie(Movie movie);

    List<ScreeningRoom> getScreeningRooms();

    Ticket getTicketById(long id);

    List<Ticket> getAllTickets();

    List<Genre> getGenres();

    List<Seat> getSeats();

    List<Seat> getAvailablePlacesForScreening(Screening screening);

    void addTicket(Ticket ticket);

    Screening getScreeningById(Long id);
}
