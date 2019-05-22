package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.Movie;
import com.capgemini.movies.domain.Screening;

public class ScreeningAdapter implements EntityAdapter<Screening, com.capgemini.movies.database.domain.Screening> {

    private static final ScreeningAdapter instance = new ScreeningAdapter();

    private ScreeningAdapter() {
    }

    @Override
    public Screening asDomainObject(com.capgemini.movies.database.domain.Screening dbEntity) {
        return new Screening(
                dbEntity.getEntityId(),
                dbEntity.getScreeningDate(),
                MovieAdapter.getInstance()
                    .asDomainObject(dbEntity.getMovie()),
                ScreeningRoomAdapter.getInstance()
                        .asDomainObject(dbEntity.getScreeningRoom())
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Screening asDbObject(Screening domainObj) {
        return null;
    }

    public static EntityAdapter<Screening, com.capgemini.movies.database.domain.Screening> getInstance() {
        return instance;
    }
}
