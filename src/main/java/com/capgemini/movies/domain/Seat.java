package com.capgemini.movies.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;


public class Seat extends Entity {
    @JsonProperty("seatNumber")
    String seatNumber;

    @JsonProperty("screeningRoom")
    @Relationship(type = "HAS", direction = Relationship.INCOMING)
    ScreeningRoom screeningRoom;

    public Seat() {
    }

    public Seat(String seatNumber, ScreeningRoom room) {
        this.seatNumber = seatNumber;
        this.screeningRoom = room;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return String.format("Seat('%s' from screeningRoom=%s)", seatNumber, screeningRoom);
    }

    public static Seat fromNumber(String seatNumber, ScreeningRoom room) {
        return new Seat(seatNumber, room);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatNumber, seat.seatNumber) &&
                Objects.equals(screeningRoom.entityId, seat.screeningRoom.entityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seatNumber, screeningRoom.entityId);
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }
}
