package com.capgemini.movies.domain;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Random;

public class Ticket {
    public static final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private String ticketNumber;
    private Screening screening;
    private Seat seat;
    private double price;
    private LocalDateTime orderDate;

    public Ticket(String ticketNumber, Screening screening, Seat seat, double price) {
        this.ticketNumber = ticketNumber;
        this.screening = screening;
        this.seat = seat;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(Screening screening, Seat seat, double price) {
        this.seat = seat;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.screening = screening;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(String ticketNumber, LocalDateTime date, Screening screening, Seat seat, double price) {
        this.ticketNumber = ticketNumber;
        this.screening = screening;
        this.seat = seat;
        this.price = price;
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(Screening screening, Seat seat, PriceList priceFromList) {
        this.seat = seat;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.screening = screening;
        this.price = priceFromList.price;
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(Screening screening, Seat seat) {
        this.seat = seat;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.screening = screening;
        this.price = PriceList.REGULAR.price;
        this.orderDate = LocalDateTime.now();
    }

    public Ticket() {
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Screening getScreening() {
        return screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return String.format("Ticket(verification number=%s, '%s', %2.2f, for " +
                        "screening=%s, with booked place:%s)",
                ticketNumber, orderDate.toString(dtFormatter),
                price, screening, seat.getSeatNumber());
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