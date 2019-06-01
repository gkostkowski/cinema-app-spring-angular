package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.ScreeningRoomBO;
import com.capgemini.movies.domain.SeatBO;

import java.util.List;
import java.util.stream.Collectors;

public class ScreeningRoomAdapter implements EntityAdapter<ScreeningRoomBO, com.capgemini.movies.database.domain.ScreeningRoom> {

    private static final ScreeningRoomAdapter instance = new ScreeningRoomAdapter();

    private ScreeningRoomAdapter() {
    }

    @Override
    public ScreeningRoomBO asDomainObject(com.capgemini.movies.database.domain.ScreeningRoom dbEntity) {
        List<SeatBO> seats = null;
        List<com.capgemini.movies.database.domain.Seat> dbSeats = dbEntity.getSeats();
        if (dbSeats != null) {
            seats = dbSeats.stream()
                    .map(s -> SeatAdapter.getInstance().asDomainObject(s))
                    .collect(Collectors.toList());
        }
        return new ScreeningRoomBO(
                dbEntity.getEntityId(),
                seats
        );
    }

    @Override
    public com.capgemini.movies.database.domain.ScreeningRoom asDbObject(ScreeningRoomBO domainObj) {
        return null;
    }

    public static ScreeningRoomAdapter getInstance() {
        return instance;
    }
}
