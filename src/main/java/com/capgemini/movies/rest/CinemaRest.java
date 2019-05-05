package com.capgemini.movies.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/services/rest")
public class CinemaRest {

    private final Map<Long, Movie> movies = new ConcurrentHashMap<Long, Movie>(
            Arrays.asList(
                    new Movie(1000L, "The Matrix", "Lilly Wachowski, Lana Wachowski",
                            1999, MovieGenre.ACTION),
                    new Movie(1001L, "The Lord of the Rings", "Ralph Bakshi", 1978,
                            MovieGenre.ANIMATION),
                    new Movie(1002L, "Pulp Fiction", "Quentin Tarantino", 1994,
                            MovieGenre.DRAMA)
                    ).stream()
                    .collect(Collectors.toMap(Movie::getId, Function.identity())));

    private final Object seqLock = new Object();

    private long sequencer = movies.values().stream()
            .max(Comparator.comparingLong(Movie::getId))
            .get().getId() + 1L;

    private long getNextValue() {
        synchronized (seqLock) {
            return sequencer++;
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return movies.values().stream()
                .sorted(Comparator.comparingLong(Movie::getId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) {
        final Movie found = movies.get(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") long id) {
        final Movie deleted = movies.remove(id);
        if (deleted != null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        final long id = Optional.ofNullable(movie.getId()).orElseGet(this::getNextValue);
        final Movie newMovie = new Movie(id, movie.getTitle(), movie.getDirecting(),
                movie.getProductionYear(), movie.getGenre());
        movies.put(id, newMovie);
        return ResponseEntity.ok(newMovie);
    }
}
