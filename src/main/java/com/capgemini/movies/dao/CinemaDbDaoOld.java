package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.Genre;
import com.capgemini.movies.domain.*;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component(value = "CinemaDbDao")
public class CinemaDbDaoOld /*implements CinemaDao*/ {
//
//    private final Session dbConn;
//
//    public CinemaDbDaoOld(Session dbConn) {
//        if (dbConn == null) {
//            throw new IllegalStateException("Database connection is not present.");
//        }
//        this.dbConn = dbConn;
//    }
//
//    @Override
//    public List<MovieTest> getMovies() {
//        return new ArrayList<>(dbConn.loadAll(MovieTest.class));
//    }
//
//    @Override
//    public List<ScreeningBO> getScreenings() {
//        return new ArrayList<>(dbConn.loadAll(ScreeningBO.class));
//    }
//
//    @Override
//    public ScreeningBO getScreeningById(Long entityId) {
//        Filter filter = getEntityIdFilter(entityId);
//        Optional<ScreeningBO> result = dbConn.loadAll(ScreeningBO.class, filter)
//                .stream()
//                .findFirst();
//        return result.orElse(null);
//    }
//
//    @Override
//    public List<ScreeningBO> getScreeningsForMovie(MovieTest movie) {
//        return getScreenings().stream()
//                .filter(s -> s.getMovie().equals(movie))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ScreeningRoomBO> getScreeningRooms() {
//        return new ArrayList<>(dbConn.loadAll(ScreeningRoomBO.class));
//    }
//
//    @Override
//    public TicketBO getTicketById(long entityId) {
//        Filter filter = getEntityIdFilter(entityId);
//        Optional<TicketBO> result = dbConn.loadAll(TicketBO.class, filter)
//                .stream()
//                .findFirst();
//        return result.orElse(null);
//    }
//
//    @Override
//    public List<TicketBO> getAllTickets() {
//        return new ArrayList<>(dbConn.loadAll(TicketBO.class));
//    }
//
//    public List<Genre> getGenres() {
//        return new ArrayList<>(dbConn.loadAll(Genre.class));
//    }
//
//    @Override
//    public List<SeatBO> getSeats() {
//        return new ArrayList<>(dbConn.loadAll(SeatBO.class));
//    }
//
//    @Override
//    public List<SeatBO> getAvailablePlacesForScreening(ScreeningBO screening) {
//        // TODO check if objects are automatically synchronized with db
//        // or if they must be fetched again
//        List<SeatBO> allSeats = screening.getScreeningRoom().getSeats();
//        List<SeatBO> bookedSeats = screening.getReservedPlaces();
//        return allSeats.stream()
//                .filter(s -> !bookedSeats.contains(s))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void addTicket(TicketBO ticket) {
//        dbConn.save(ticket);
//    }
//
//    private Filter getEntityIdFilter(long entityId) {
//        return new Filter("entityId",
//                ComparisonOperator.EQUALS,
//                entityId);
//    }
}
