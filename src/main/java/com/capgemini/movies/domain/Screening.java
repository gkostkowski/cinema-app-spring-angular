package com.capgemini.movies.domain;

import com.capgemini.movies.database.util.CustomDateConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDateTime;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//@JsonSerialize(using = ScreeningSerializer.class)
public class Screening extends Entity {

    @JsonProperty("entityId")
    private Long entityId;

    @Convert(CustomDateConverter.class)
    @JsonProperty("screeningDate")
    LocalDateTime screeningDate;

//    @JsonProperty("movie")
    @JsonIgnore
    @Relationship(type = "SHOWS", direction = Relationship.OUTGOING)
    Movie movie;

    @JsonProperty("screeningRoom")
    @Relationship(type = "SHOWED_IN", direction = Relationship.OUTGOING)
    ScreeningRoom screeningRoom;

    @JsonProperty("tickets")
    @Relationship(type = "BOUGHT_FOR", direction = Relationship.INCOMING)
    List<Ticket> tickets;

    public Screening() {
        tickets = new LinkedList<>();
//        movieTitle = "";
//        if ( movie != null) {
//            movieTitle = movie.title;
//        }

    }

    public Movie getMovie() {
        return movie;
    }

    @JsonProperty(value = "movie")
    public String getMovieTitle() {
        return movie.title;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Long getEntityId() {
        return entityId;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public List<Seat> getReservedPlaces() {
        return tickets.stream()
                .map(t -> t.bookedPlace)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Screening(%d, '%s', %s, %s)",
                entityId,
                screeningDate.toString(CustomDateConverter.dtFormatter),
                screeningRoom, movie);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
