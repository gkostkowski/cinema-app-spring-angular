package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.ScreeningRoom;
import com.capgemini.movies.domain.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class ScreeningRoomAdapter implements EntityAdapter<ScreeningRoom, com.capgemini.movies.database.domain.ScreeningRoom> {

    private static final ScreeningRoomAdapter instance = new ScreeningRoomAdapter();

    private ScreeningRoomAdapter() {
    }

    @Override
    public ScreeningRoom asDomainObject(com.capgemini.movies.database.domain.ScreeningRoom dbEntity) {
        List<Seat> seats = null;
        List<com.capgemini.movies.database.domain.Seat> dbSeats = dbEntity.getSeats();
        if (dbSeats != null) {
            seats = dbSeats.stream()
                    .map(s -> SeatAdapter.getInstance().asDomainObject(s))
                    .collect(Collectors.toList());
        }
        return new ScreeningRoom(
                dbEntity.getEntityId(),
                seats
        );
    }

    @Override
    public com.capgemini.movies.database.domain.ScreeningRoom asDbObject(ScreeningRoom domainObj) {
        return null;
    }

    public static ScreeningRoomAdapter getInstance() {
        return instance;
    }
}
