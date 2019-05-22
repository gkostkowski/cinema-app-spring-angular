package com.capgemini.movies.dao;

import com.capgemini.movies.adapter.MovieAdapter;
import com.capgemini.movies.adapter.ScreeningAdapter;
import com.capgemini.movies.adapter.ScreeningRoomAdapter;
import com.capgemini.movies.adapter.TicketAdapter;
import com.capgemini.movies.database.domain.*;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component(value = "CinemaDbDao")
public class CinemaDbDao implements CinemaDao {

    private final Session dbConn;

    public CinemaDbDao(Session dbConn) {
        if (dbConn == null) {
            throw new IllegalStateException("Database connection is not present.");
        }
        this.dbConn = dbConn;
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList <>(dbConn.loadAll(Movie.class));
    }

    @Override
    public List<Screening> getScreenings() {
        return new ArrayList<>(dbConn.loadAll(Screening.class));
    }

    @Override
    public Screening getScreeningById(Long id) {
        Filter filter = getEntityIdFilter(id);
        Optional<Screening> result = dbConn
                .loadAll(Screening.class, filter)
                .stream()
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public List<Screening> getScreeningsForMovie(Movie movie) {
        return getScreenings().stream()
                .filter(s -> s.getMovie().equals(movie))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScreeningRoom> getScreeningRooms() {
        return new ArrayList<>(dbConn.loadAll(ScreeningRoom.class));

    }

    @Override
    public Ticket getTicketById(long id) {
        Filter filter = getEntityIdFilter(id);
        Optional<com.capgemini.movies.database.domain.Ticket> result =
                dbConn.loadAll(Ticket.class, filter)
                .stream()
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(dbConn.loadAll(Ticket.class));
    }

    public List<Genre> getGenres() {
        return new ArrayList<>(dbConn.loadAll(Genre.class));
    }

    @Override
    public List<Seat> getSeats() {
        return new ArrayList<>(dbConn.loadAll(Seat.class));
    }

    @Override
    public List<Seat> getAvailablePlacesForScreening(Screening screening) {
        // TODO check if objects are automatically synchronized with db
        // or if they must be fetched again
        List<Seat> allSeats = screening.getScreeningRoom().getSeats();
        List<Seat> bookedSeats = screening.getReservedPlaces();
        return allSeats.stream()
                .filter(s -> !bookedSeats.contains(s))
                .collect(Collectors.toList());
    }

    @Override
    public void addTicket(Ticket ticket) {
        dbConn.save(ticket);
    }

    private Filter getEntityIdFilter(long entityId) {
        return new Filter("entityId",
                ComparisonOperator.EQUALS,
                entityId);
    }
}
