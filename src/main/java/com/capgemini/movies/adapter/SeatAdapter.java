package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.Seat;

public class SeatAdapter implements EntityAdapter<Seat, com.capgemini.movies.database.domain.Seat> {
    private static SeatAdapter instance = new SeatAdapter();

    private SeatAdapter() {
    }

    @Override
    public Seat asDomainObject(com.capgemini.movies.database.domain.Seat dbEntity) {
        return null;
    }

    @Override
    public com.capgemini.movies.database.domain.Seat asDbObject(Seat domainObj) {
        return null;
    }

    public static SeatAdapter getInstance() {
        return instance;
    }
}
