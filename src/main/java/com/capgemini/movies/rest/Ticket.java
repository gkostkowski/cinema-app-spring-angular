package com.capgemini.movies.rest;

import java.util.Random;

public class Ticket {
    private String ticketNumber;
    private Screening screening;
    private Seat seat;
    private double price;

    public Ticket(String ticketNumber, Screening screening, Seat seat, double price) {
        this.ticketNumber = ticketNumber;
        this.screening = screening;
        this.seat = seat;
        this.price = price;
    }

    public Ticket(String ticketNumber, Screening screening, Seat seat, PriceList price) {
        this.ticketNumber = ticketNumber;
        this.screening = screening;
        this.seat = seat;
        this.price = price.price;
    }

    public Ticket(Screening screening, Seat seat, double price) {
        this.seat = seat;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.screening = screening;
        this.price = price;
    }

    public Ticket(Screening screening, Seat seat) {
        this.seat = seat;
        this.ticketNumber = TicketNumberGenerator.generateNextTicketNo();
        this.screening = screening;
        this.price = PriceList.REGULAR.price;
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
}

class TicketNumberGenerator {
    private static int numberLength = 8;
    private static Random rand = new Random();
    private static char firstChar = 'A';
    private static char lastChar = 'Z';

    static String generateNextTicketNo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberLength; i++) {
            sb.append(rand.nextInt((lastChar - firstChar) + 1) + firstChar);
        }
        return sb.toString();
    }
}