package com.capgemini.movies.rest;

import com.capgemini.movies.database.domain.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/services/rest")
public class CinemaRest {

//    private final Map<Long, Movie> movies;

    private final Object seqLock = new Object();
    private final CinemaService service;

    private long sequencer;

    private long getNextValue() {
        synchronized (seqLock) {
            return sequencer++;
        }
    }

    public CinemaRest(CinemaService service) {
        this.service = service;
//        sequencer = service.getMoviesMap().values().stream()
//                .max(Comparator.comparingLong(Movie::getEntityId))
//                .get().getEntityId() + 1L;
        sequencer = 1L;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return service.getMoviesMap().values().stream()
                .sorted(Comparator.comparingLong(Movie::getEntityId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/movie/{id}/screenings", method = RequestMethod.GET)
    public List<Screening> getScreeningsForMovie(@PathVariable("id") long id) {
        Movie movie = service.getMoviesMap().get(id);
        return service.getScreeningsForMovieMap(movie).values().stream()
                .sorted(Comparator.comparingLong(Screening::getEntityId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) {
        final Movie found = service.getMoviesMap().get(id);
        if (found != null) {
            return ResponseEntity.ok(found);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") long id) {
        final Movie deleted = service.getMoviesMap().remove(id);
        if (deleted != null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        final long id = Optional.ofNullable(movie.getEntityId()).orElseGet(this::getNextValue);
        final Movie newMovie = new Movie(id, movie.getTitle(), movie.getDirecting(),
                movie.getDescription(), movie.getProductionYear(), movie.getGenres());
        service.getMoviesMap().put(id, newMovie);
        return ResponseEntity.ok(newMovie);
    }

    @RequestMapping(value = "/movies/img/{id}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMovieImage(@PathVariable("id") long id) throws IOException {

        ClassPathResource imgFile = new ClassPathResource(
                String.format("image/movies/%d.jpg", id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
