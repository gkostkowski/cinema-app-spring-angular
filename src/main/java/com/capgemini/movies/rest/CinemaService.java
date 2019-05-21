package com.capgemini.movies.rest;

import com.capgemini.movies.dao.CinemaDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    private CinemaDao dao;
    private Collection<Ticket> orderedTickets = new ArrayList<>();

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

    public Map<Long, Screening> getScreenings() {
        List<Screening> screenings = dao.getScreenings();
        return new ConcurrentHashMap<Long, Screening>(screenings.stream()
                .collect(Collectors.toMap(Screening::getId, Function.identity())));
    }

    public void addTicket(Ticket ticket) {
        orderedTickets.add(ticket);
    }
}
