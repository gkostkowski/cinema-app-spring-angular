package com.capgemini.movies.database.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


public class ScreeningRoom extends Entity {

    @JsonProperty("entityId")
    Long entityId;

    @JsonProperty("places")
    int places;

    @JsonProperty("seats")
    @Relationship(type = "HAS", direction = Relationship.OUTGOING)
    List<Seat> seats;

    public ScreeningRoom() {
    }

    @Override
    public String toString() {
        return String.format("ScreeningRoom(%d, with %d places)",
                entityId,
                places);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Long getEntityId() {
        return entityId;
    }

    public int getPlaces() {
        return places;
    }
}
