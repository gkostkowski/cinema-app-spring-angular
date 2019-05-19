package com.capgemini.movies.dao;

import com.capgemini.movies.rest.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component(value = "CinemaDao")
public interface CinemaDao {
    List<Movie> getMovies();

    List<Screening> getScreenings();

    List<Screening> getScreeningsForMovie(Movie movie);

    List<Seat> getAllPlaces(Screening screening);

    List<Seat> getFreePlaces(Screening screening);

    Seat getNextFreePlace(Screening screening);

    Ticket generateTicket(Screening screening, PriceList price);

    boolean isTicketValid(Ticket verifiedTicket, Screening relatedScreening);
}
