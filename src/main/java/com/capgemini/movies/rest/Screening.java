package com.capgemini.movies.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class Screening {
    private Long id;
    private LocalDateTime screeningDate;  // TODO change date format add hour minutes
    private Movie movie;
    private ScreeningRoom screeningRoom;

    public Screening() {
    }

    public Screening(Long id, LocalDateTime screeningDate, Movie movie, ScreeningRoom screeningRoom) {
        this.id = id;
        this.screeningDate = screeningDate;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
//        this.orderedTickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public Optional<Seat> getFirstFreePlace() {
        return screeningRoom.getFirstFreeSeat();
    }

    public void setSeatAsTaken(Seat seat) {
        screeningRoom.setSeatAsTaken(seat);
    }
}
