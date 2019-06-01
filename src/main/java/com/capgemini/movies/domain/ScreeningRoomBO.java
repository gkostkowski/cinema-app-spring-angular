package com.capgemini.movies.domain;

import java.util.ArrayList;
import java.util.List;

public class ScreeningRoomBO {
    private final Long id;
    private final List<SeatBO> seats;
    private static final char firstRow = 'A';

    public ScreeningRoomBO() {
        seats = new ArrayList<>();
        id = 0L;
    }

    public ScreeningRoomBO(long id, List<SeatBO> seats) {
        this.id = id;
        this.seats = seats;
    }

    public ScreeningRoomBO(long id, int noOfRows, int noOfColumns) {
        this.id = id;
        this.seats = makeScreeningRoomPlaces(noOfRows, noOfColumns);
    }


    public static List<SeatBO> makeScreeningRoomPlaces(int noOfRows,
                                                       int noOfColumns) {
        List<SeatBO> seats = new ArrayList<>(noOfColumns * noOfRows);
        for (int i = 0; i < noOfRows; i++) {
            char row = (char) (ScreeningRoomBO.firstRow + i);
            for (int j = 1; j <= noOfColumns; j++) {
                seats.add(new SeatBO(row, j));
            }
        }
        return seats;
    }

    public Long getId() {
        return id;
    }

    public List<SeatBO> getSeats() {
        return seats;
    }

    public static char getFirstRow() {
        return firstRow;
    }
}