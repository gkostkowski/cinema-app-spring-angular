package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CinemaRepositoryDao")
public class CinemaRepositoryDao implements CinemaDao {


    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository scrRoomRepository;
    private final TicketRepository ticketRepository;

    public CinemaRepositoryDao(@Autowired MovieRepository movieRepository,
                               @Autowired ScreeningRepository screeningRepository,
                               @Autowired ScreeningRoomRepository scrRoomRepository,
                               @Autowired TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
        this.scrRoomRepository = scrRoomRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAllFull();
    }

    @Override
    public List<Screening> getScreenings() {
//        return screeningRepository.findAll();
        return screeningRepository.findAllFull();
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
    public Ticket getTicketById(String id) {
        return ticketRepository.findByTicketNumber(id);
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
        return screeningRepository.findFreePlacesForScreening(screening.getEntityId());
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.addTicket(ticket.getTicketNumber(), ticket.getScreening().getEntityId(),
                ticket.getBookedPlace().getSeatNumber(),
                ticket.getTextualOrderDate(), ticket.getPrice());
    }

    @Override
    public Screening getScreeningById(Long id) {
        return screeningRepository.findByIdFull(id);
    }

    @Override
    public ScreeningRoom getScreeningRoomByScreeningId(long screeningId) {
        return this.scrRoomRepository.findByScreeningRoomId(screeningId);
    }

    @Override
    public Movie getMoviesByScreening(Long entityId) {
        return movieRepository.findByScreeningId(entityId);
    }
}
