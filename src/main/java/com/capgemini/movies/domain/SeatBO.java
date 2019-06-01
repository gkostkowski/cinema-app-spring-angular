package com.capgemini.movies.domain;

public class SeatBO {
    private String seatNumber;
    private Boolean isFree = true;

    public SeatBO() {
    }

    public SeatBO(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatBO(String row, Integer column) {
        this.seatNumber = row + column.toString();
    }

    public SeatBO(Character row, Integer column) {
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
