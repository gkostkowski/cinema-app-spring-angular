package com.capgemini.movies.rest;

import java.util.ArrayList;
import java.util.List;

public class ScreeningRoom {
    private final Long id;
    private final List<Seat> seats;
    private static final char firstRow = 'A';

    public ScreeningRoom() {
        seats = new ArrayList<>();
        id = 0L;
    }

    public ScreeningRoom(long id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public ScreeningRoom(long id, int noOfRows, int noOfColumns) {
        this.id = id;
        this.seats = makeScreeningRoomPlaces(noOfRows, noOfColumns);
    }


    public static List<Seat> makeScreeningRoomPlaces(int noOfRows,
                                                      int noOfColumns) {
        List<Seat> seats = new ArrayList<>(noOfColumns * noOfRows);
        for (int i = 0; i < noOfRows; i++) {
            char row = (char) (ScreeningRoom.firstRow + i);
            for (int j = 1; j <= noOfColumns; j++) {
                seats.add(new Seat(row, j));
            }
        }
        return seats;
    }

    public Long getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public static char getFirstRow() {
        return firstRow;
    }
}