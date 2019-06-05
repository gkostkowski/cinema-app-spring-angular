package com.capgemini.movies.database.domain;

import com.capgemini.movies.database.util.CustomDateConverter;
import com.capgemini.movies.database.util.TicketSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDateTime;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.Random;

@JsonSerialize(using = TicketSerializer.class)
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

    public Ticket(Screening screening, Seat place, Double price) {
        this.orderDate = LocalDateTime.now();
        this.price = price;
        this.screening = screening;
        this.bookedPlace = place;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.entityId = this.getInternalId();
    }

    public Ticket(Screening screening, Seat place) {
        this.orderDate = LocalDateTime.now();
        this.price = PriceList.REGULAR.price;
        this.screening = screening;
        this.bookedPlace = place;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
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

    public String getTextualOrderDate() {
        return orderDate.toString(CustomDateConverter.dtFormatter);
    }

    @Override
    public String toString() {
        return String.format("Ticket(%d, number=%s, '%s', %f, for screening=%s, with booked" +
                        " place:%s)",
                entityId, ticketNumber,
                orderDate != null ? orderDate.toString(CustomDateConverter.dtFormatter) : null,
                price, screening,
                bookedPlace != null ? bookedPlace.seatNumber : 0);
    }
}

class TicketNumberGenerator {
    private static int numberLength = 8;
    private static Random rand = new Random();
    private static char firstChar = 'A';
    private static char lastChar = 'Z';

    static String generateNextTicketNo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberLength; i++) {
            char nextChar = (char) (rand.nextInt((lastChar - firstChar) + 1) + firstChar);
            sb.append(nextChar);
        }
        return sb.toString();
    }
}

enum PriceList {
    REGULAR(20),
    STUDENT(18),
    CHILD(12);

    public final double price;

    PriceList(double price) {
        this.price = price;
    }

    PriceList() {
        price = 0;
    }

    public double getPrice() {
        return price;
    }
}
