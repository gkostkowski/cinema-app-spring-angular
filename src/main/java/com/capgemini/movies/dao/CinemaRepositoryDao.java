package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CinemaRepositoryDao")
public class CinemaRepositoryDao implements CinemaDao {


    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    public CinemaRepositoryDao(@Autowired MovieRepository movieRepository,
                               @Autowired ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAllFull();
    }

    @Override
    public List<Screening> getScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningRepository.findScreeningByMovie(movie);
    }

    @Override
    public List<Screening> getScreeningsForMovieTitle(String title) {
        return screeningRepository.findScreeningByMovie_Title(title);
    }

    @Override
    public List<ScreeningRoom> getScreeningRooms() {
        return null;
    }

    @Override
    public Ticket getTicketById(long id) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public List<Genre> getGenres() {
        return null;
    }

    @Override
    public List<Seat> getSeats() {
        return null;
    }

    @Override
    public List<Seat> getAvailablePlacesForScreening(Screening screening) {
        return null;
    }

    @Override
    public void addTicket(Ticket ticket) {

    }

    @Override
    public Screening getScreeningById(Long id) {
        return null;
    }
}
