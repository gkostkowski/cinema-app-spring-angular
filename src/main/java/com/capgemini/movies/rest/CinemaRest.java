package com.capgemini.movies.rest;

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
                            "What if virtual reality wasn't just for fun, but was being used to imprison you? That's the dilemma that faces mild-mannered computer jockey Thomas Anderson (Keanu Reeves) in The Matrix. It's the year 1999, and Anderson (hacker alias: Neo) works in a cubicle, manning a computer and doing a little hacking on the side. It's through this latter activity that Thomas makes the acquaintance of Morpheus (Laurence Fishburne), who has some interesting news for Mr. Anderson -- none of what's going on around him is real. The year is actually closer to 2199, and it seems Thomas, like most people, is a victim of The Matrix, a massive artificial intelligence system that has tapped into people's minds and created the illusion of a real world, while using their brains and bodies for energy, tossing them away like spent batteries when they're through. Morpheus, however, is convinced Neo is \"The One\" who can crack open The Matrix and bring his people to both physical and psychological freedom. The Matrix is the second feature film from the sibling writer/director team of Andy Wachowski and Larry Wachowski, who made an impressive debut with the stylish erotic crime thriller Bound.",
                            1999, MovieGenre.ACTION),
                    new Movie(1001L, "The Lord of the Rings: The Two Towers", "Peter Jackson",
                            "Frodo and Samwise press on toward Mordor. Gollum insists on being the guide. Can anyone so corrupted by the ring be trusted? Can Frodo, increasingly under the sway of the ring, even trust himself? Meanwhile, Aragorn, drawing closer to his kingly destiny, rallies forces of good for the battles that must come. Director Peter Jackson delivers an amazing second movie that won 2 Academy Awards (R)* and earned 6 total nominations including Best Picture. The journey continues. So do the astonishing spectacle and splendor.",2002,
                            MovieGenre.ANIMATION),
                    new Movie(1002L, "Pulp Fiction", "Quentin Tarantino", "Outrageously violent, time-twisting, and in love with language, Pulp Fiction was widely considered the most influential American movie of the 1990s. Director and co-screenwriter Quentin Tarantino synthesized such seemingly disparate traditions as the syncopated language of David Mamet; the serious violence of American gangster movies, crime movies, and films noirs mixed up with the wacky violence of cartoons, video games, and Japanese animation; and the fragmented story-telling structures of such experimental classics as Citizen Kane, Rashomon, and La jetée. The Oscar-winning script by Tarantino and Roger Avary intertwines three stories, featuring Samuel L. Jackson and John Travolta, in the role that single-handedly reignited his career, as hit men who have philosophical interchanges on such topics as the French names for American fast food products; Bruce Willis as a boxer out of a 1940s B-movie; and such other stalwarts as Harvey Keitel, Tim Roth, Christopher Walken, Eric Stoltz, Ving Rhames, and Uma Thurman, whose dance sequence with Travolta proved an instant classic.",1994,
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
                movie.getDescription(), movie.getProductionYear(), movie.getGenre());
        movies.put(id, newMovie);
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
