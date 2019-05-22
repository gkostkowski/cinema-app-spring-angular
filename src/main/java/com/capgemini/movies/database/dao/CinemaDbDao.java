package com.capgemini.movies.database.dao;

import com.capgemini.movies.adapter.MovieAdapter;
import com.capgemini.movies.adapter.ScreeningAdapter;
import com.capgemini.movies.adapter.ScreeningRoomAdapter;
import com.capgemini.movies.adapter.TicketAdapter;
import com.capgemini.movies.database.domain.Genre;
import com.capgemini.movies.domain.*;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<com.capgemini.movies.database.domain.Movie> movies =
                new ArrayList <>(dbConn.loadAll(com.capgemini.movies.database.domain.Movie.class));
        return movies.stream()
                .map(m -> MovieAdapter.getInstance().asDomainObject(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<Screening> getScreenings() {
        ArrayList<com.capgemini.movies.database.domain.Screening> screenings =
                new ArrayList<>(dbConn.loadAll(com.capgemini.movies.database.domain.Screening.class));
        return screenings.stream()
                .map(m -> ScreeningAdapter.getInstance().asDomainObject(m))
                .collect(Collectors.toList());
    }

    @Override
    public Screening getScreeningById(Long id) {
        Filter filter = getEntityIdFilter(id);
        Optional<com.capgemini.movies.database.domain.Screening> result = dbConn
                .loadAll(com.capgemini.movies.database.domain.Screening.class, filter)
                .stream()
                .findFirst();
        if (result.isPresent()) {
            return ScreeningAdapter.getInstance().asDomainObject(result.get());
        }
        return null;
    }

    @Override
    public List<Screening> getScreeningsForMovie(Movie movie) {
        return getScreenings().stream()
                .filter(s -> s.getMovie().equals(movie))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScreeningRoom> getScreeningRooms() {
        ArrayList<com.capgemini.movies.database.domain.ScreeningRoom> screeningRooms =
                new ArrayList<>(dbConn.loadAll(com.capgemini.movies.database.domain.ScreeningRoom.class));
        return screeningRooms.stream()
                .map(s -> ScreeningRoomAdapter.getInstance().asDomainObject(s))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicketById(long id) {
        Filter filter = getEntityIdFilter(id);
        Optional<com.capgemini.movies.database.domain.Ticket> result =
                dbConn.loadAll(com.capgemini.movies.database.domain.Ticket.class, filter)
                .stream()
                .findFirst();
        if (result.isPresent()) {
            return TicketAdapter.getInstance().asDomainObject(result.get());
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        ArrayList<com.capgemini.movies.database.domain.Ticket> tickets =
                new ArrayList<>(dbConn.loadAll(com.capgemini.movies.database.domain.Ticket.class));
        return tickets.stream()
                .map(t -> TicketAdapter.getInstance().asDomainObject(t))
                .collect(Collectors.toList());
    }

    public List<MovieGenre> getGenres() {
        ArrayList<Genre> genres = new ArrayList<>(dbConn.loadAll(Genre.class));
        return genres.stream()
                .map(g -> new MovieGenre(g.getName()))
                .collect(Collectors.toList());
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
        com.capgemini.movies.database.domain.Ticket dbTicket =
                TicketAdapter.getInstance().asDbObject(ticket);
        dbConn.save(dbTicket);
    }

    private Filter getEntityIdFilter(long entityId) {
        return new Filter("entityId",
                ComparisonOperator.EQUALS,
                entityId);
    }
}
