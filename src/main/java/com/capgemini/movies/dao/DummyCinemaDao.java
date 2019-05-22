package com.capgemini.movies.dao;

import com.capgemini.movies.domain.Movie;
import com.capgemini.movies.domain.MovieGenre;
import com.capgemini.movies.domain.Screening;
import com.capgemini.movies.domain.ScreeningRoom;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "DummyCinemaDao")
public class DummyCinemaDao implements CinemaDao {

    private DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    public DummyCinemaDao() {
    }

    @Override
    public List<Movie> getMovies() {
        return Arrays.asList(
                new Movie(1000L, "The Matrix", "Lilly Wachowski, Lana Wachowski",
                        "What if virtual reality wasn't just for fun, but was being used to imprison you? That's the dilemma that faces mild-mannered computer jockey Thomas Anderson (Keanu Reeves) in The Matrix. It's the year 1999, and Anderson (hacker alias: Neo) works in a cubicle, manning a computer and doing a little hacking on the side. It's through this latter activity that Thomas makes the acquaintance of Morpheus (Laurence Fishburne), who has some interesting news for Mr. Anderson -- none of what's going on around him is real. The year is actually closer to 2199, and it seems Thomas, like most people, is a victim of The Matrix, a massive artificial intelligence system that has tapped into people's minds and created the illusion of a real world, while using their brains and bodies for energy, tossing them away like spent batteries when they're through. Morpheus, however, is convinced Neo is \"The One\" who can crack open The Matrix and bring his people to both physical and psychological freedom. The Matrix is the second feature film from the sibling writer/director team of Andy Wachowski and Larry Wachowski, who made an impressive debut with the stylish erotic crime thriller Bound.",
                        1999, MovieGenre.ACTION),
                new Movie(1001L, "The Lord of the Rings: The Two Towers", "Peter Jackson",
                        "Frodo and Samwise press on toward Mordor. Gollum insists on being the guide. Can anyone so corrupted by the ring be trusted? Can Frodo, increasingly under the sway of the ring, even trust himself? Meanwhile, Aragorn, drawing closer to his kingly destiny, rallies forces of good for the battles that must come. Director Peter Jackson delivers an amazing second movie that won 2 Academy Awards (R)* and earned 6 total nominations including Best Picture. The journey continues. So do the astonishing spectacle and splendor.",2002,
                        MovieGenre.ANIMATION),
                new Movie(1002L, "Pulp Fiction", "Quentin Tarantino", "Outrageously violent, time-twisting, and in love with language, Pulp Fiction was widely considered the most influential American movie of the 1990s. Director and co-screenwriter Quentin Tarantino synthesized such seemingly disparate traditions as the syncopated language of David Mamet; the serious violence of American gangster movies, crime movies, and films noirs mixed up with the wacky violence of cartoons, video games, and Japanese animation; and the fragmented story-telling structures of such experimental classics as Citizen Kane, Rashomon, and La jetée. The Oscar-winning script by Tarantino and Roger Avary intertwines three stories, featuring Samuel L. Jackson and John Travolta, in the role that single-handedly reignited his career, as hit men who have philosophical interchanges on such topics as the French names for American fast food products; Bruce Willis as a boxer out of a 1940s B-movie; and such other stalwarts as Harvey Keitel, Tim Roth, Christopher Walken, Eric Stoltz, Ving Rhames, and Uma Thurman, whose dance sequence with Travolta proved an instant classic.",1994,
                        MovieGenre.DRAMA)
        );
    }

    @Override
    public List<Screening> getScreenings() {
        List<ScreeningRoom> rooms = getScreeningRooms();
        List<Movie> movies = getMovies();
        return Arrays.asList(  // TODO add hour minutes
                new Screening(2001L, LocalDateTime.parse("2019-05-12 17:00",dtFormatter),
                        movies.get(0), rooms.get(0)),
                new Screening(2002L, LocalDateTime.parse("2019-05-12 19:15",dtFormatter),
                        movies.get(0), rooms.get(1)),
                new Screening(2003L, LocalDateTime.parse("2019-05-13 17:30",dtFormatter),
                        movies.get(1), rooms.get(0)),
                new Screening(2004L, LocalDateTime.parse("2019-05-13 17:30",dtFormatter),
                        movies.get(2), rooms.get(1)),
                new Screening(2005L, LocalDateTime.parse("2019-05-14 15:00",dtFormatter),
                        movies.get(0), rooms.get(0)));
    }

    @Override
    public List<Screening> getScreeningsForMovie(Movie movie) {
        return getScreenings().stream()
                .filter(s -> s.getMovie().equals(movie))
                .collect(Collectors.toList());
    }

    private List<ScreeningRoom> getScreeningRooms() {
        return Arrays.asList(
                new ScreeningRoom(3001L, 10, 12),
                new ScreeningRoom(3002L, 19, 10)
        );
    }

}
