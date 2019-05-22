package com.capgemini.movies.rest;

import com.capgemini.movies.dao.CinemaDao;
import com.capgemini.movies.domain.Movie;
import com.capgemini.movies.domain.Screening;
import com.capgemini.movies.domain.Seat;
import com.capgemini.movies.domain.Ticket;
import com.capgemini.movies.rest.exception.ReservationAfterScreeningException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    private CinemaDao dao;

    public CinemaService(@Qualifier("DummyCinemaDao") CinemaDao dao) {
        this.dao = dao;
    }

    public Map<Long, Movie> getMoviesMap() {
        List<Movie> movies = dao.getMovies();
        return new ConcurrentHashMap<Long, Movie>(movies.stream()
                .collect(Collectors.toMap(Movie::getId, Function.identity())));
    }

    public Map<Long, Screening> getScreeningsForMovieMap(Movie movie) {
        List<Screening> screenings = dao.getScreeningsForMovie(movie);
        return new ConcurrentHashMap<Long, Screening>(screenings.stream()
                .collect(Collectors.toMap(Screening::getId, Function.identity())));
    }

    public Screening getScreeningById(Long id) {
        return dao.getScreeningById(id);
    }

    public Optional<Ticket> makeTicketReservation(Screening screening,
                                                  Seat chosenPlace)
            throws ReservationAfterScreeningException {
        LocalDateTime currentDate = LocalDateTime.now();
        currentDate.isAfter(currentDate);
        if (screening.getScreeningDate().isAfter(currentDate)) {
            throw new ReservationAfterScreeningException();
        }
        List<Seat> availablePlaces = dao.getAvailablePlacesForScreening(screening);
        if (availablePlaces.contains(chosenPlace)) {
            edu.cinema.domain.Ticket newTicket =
                    new edu.cinema.domain.Ticket(screening, chosenPlace); //with standard price
            dao.addTicket(new Ticket(newTicket.getTicketNumber(), screening,
                    chosenPlace, newTicket.getPrice()));
            return Optional.of(newTicket);
        }
        return Optional.empty();
    }

    public Optional<edu.cinema.domain.Ticket> makeTicketReservation(Screening screening)
            throws ReservationAfterScreeningException {
        LocalDateTime currentDate = LocalDateTime.now();
        currentDate.isAfter(currentDate);
        if (screening.getScreeningDate().isAfter(currentDate)) {
            throw new ReservationAfterScreeningException();
        }
        List<Seat> availablePlaces = dao.getAvailablePlacesForScreening(screening);
        if (availablePlaces.isEmpty()) {
            return Optional.empty();  //no more free places
        }
        Seat chosenPlace = availablePlaces.get(0);
        edu.cinema.domain.Ticket newTicket =
                new edu.cinema.domain.Ticket(screening, chosenPlace); //with standard price
        dao.addTicket(new Ticket(newTicket.getTicketNumber(), screening, chosenPlace,
                newTicket.getPrice()));
        return Optional.of(newTicket);

    }

    public boolean isTicketValid(Ticket checkedTicket) {
        List<Ticket> allTickets = checkedTicket.getScreening().getTickets();
        Seat reservedSeat = checkedTicket.getBookedPlace();
        String verificationCode = checkedTicket.getTicketNumber();
        return allTickets.stream()
                .anyMatch(t -> t.getBookedPlace().equals(reservedSeat) &&
                        t.getTicketNumber().equals(verificationCode));
    }

    public boolean isTicketValid(String verificationCode, Long screeningNumber) {
        Screening scr = getScreeningById(screeningNumber);
        if (scr != null) {
            List<Ticket> allTickets = scr.getTickets();
            return allTickets.stream()
                    .anyMatch(t -> t.getTicketNumber() != null
                            && t.getTicketNumber().equals(verificationCode));
        }
        return false;
    }
}
