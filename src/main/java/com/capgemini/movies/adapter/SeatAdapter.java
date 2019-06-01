package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.SeatBO;

public class SeatAdapter implements EntityAdapter<SeatBO, com.capgemini.movies.database.domain.Seat> {
    private static SeatAdapter instance = new SeatAdapter();

    private SeatAdapter() {
    }

    @Override
    public SeatBO asDomainObject(com.capgemini.movies.database.domain.Seat dbEntity) {
        return null;
    }

    @Override
    public com.capgemini.movies.database.domain.Seat asDbObject(SeatBO domainObj) {
        return null;
    }

    public static SeatAdapter getInstance() {
        return instance;
    }
}
