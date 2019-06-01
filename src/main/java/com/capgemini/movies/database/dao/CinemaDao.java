package com.capgemini.movies.database.dao;

import com.capgemini.movies.database.domain.*;

import java.util.List;

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
