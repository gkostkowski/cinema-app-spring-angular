package com.capgemini.movies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class ScreeningBO {
    private Long id;
    private LocalDateTime screeningDate;
    private MovieBO movie;
    private ScreeningRoomBO screeningRoom;
    private Collection<TicketBO> orderedTickets;

    public ScreeningBO() {
    }

    public ScreeningBO(Long id, LocalDateTime screeningDate, MovieBO movie, ScreeningRoomBO screeningRoom) {
        this.id = id;
        this.screeningDate = screeningDate;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
        this.orderedTickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public MovieBO getMovie() {
        return movie;
    }

    public ScreeningRoomBO getScreeningRoom() {
        return screeningRoom;
    }

    public Collection<TicketBO> getOrderedTickets() {
        return orderedTickets;
    }

}
