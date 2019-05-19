package com.capgemini.movies.rest;

public class Seat {
    private String seatNumber;
    private Boolean isFree = true;

    public Seat() {
    }

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Seat(String row, Integer column) {
        this.seatNumber = row + column.toString();
    }

    public Seat(Character row, Integer column) {
        this(row.toString(), column);
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Boolean isFree() {
        return this.isFree;
    }

    public void reservePlace() {
        this.isFree = false;
    }
}
