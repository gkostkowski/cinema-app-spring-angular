package com.capgemini.movies.database.domain;

import com.capgemini.movies.database.util.CustomDateConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalDateTime;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

public class Ticket extends Entity {

    @JsonProperty("entityId")
    private Long entityId;

    @Convert(CustomDateConverter.class)
    @JsonProperty("orderDate")
    LocalDateTime orderDate;

    @JsonProperty("ticketNumber")
    String ticketNumber;

    @JsonProperty("price")
    Double price;

    @JsonProperty("screening")
    @Relationship(type = "BOUGHT_FOR", direction = Relationship.OUTGOING)
    Screening screening;

    @JsonProperty("bookedPlace")
    @Relationship(type = "WITH_BOOKED_PLACE", direction = Relationship.OUTGOING)
    Seat bookedPlace;

    public Ticket() {
    }

    public Ticket(String ticketNumber, Screening screening, LocalDateTime orderDate, Seat place, Double price) {
        this.orderDate = orderDate;
        this.price = price;
        this.screening = screening;
        this.bookedPlace = place;
        this.entityId = this.getInternalId();
    }

    public Ticket(String ticketNumber, Screening screening, Seat place, Double price) {
        this.orderDate = LocalDateTime.now();
        this.price = price;
        this.screening = screening;
        this.bookedPlace = place;
        this.ticketNumber = ticketNumber;
        this.entityId = this.getInternalId();
    }

    public Seat getBookedPlace() {
        return bookedPlace;
    }

    public Screening getScreening() {
        return screening;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Double getPrice() {
        return price;
    }

    public Long getEntityId() {
        return entityId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return String.format("Ticket(%d, number=%s, '%s', %f, for screening=%s, with booked" +
                        " place:%s)",
                entityId, ticketNumber, orderDate.toString(CustomDateConverter.dtFormatter),
                price, screening, bookedPlace.seatNumber);
    }
}
