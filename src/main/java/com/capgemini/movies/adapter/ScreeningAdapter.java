package com.capgemini.movies.adapter;

import com.capgemini.movies.domain.ScreeningBO;

public class ScreeningAdapter implements EntityAdapter<ScreeningBO, com.capgemini.movies.database.domain.Screening> {

    private static final ScreeningAdapter instance = new ScreeningAdapter();

    private ScreeningAdapter() {
    }

    @Override
    public ScreeningBO asDomainObject(com.capgemini.movies.database.domain.Screening dbEntity) {
        return new ScreeningBO(
                dbEntity.getEntityId(),
                dbEntity.getScreeningDate(),
                MovieAdapter.getInstance()
                    .asDomainObject(dbEntity.getMovie()),
                ScreeningRoomAdapter.getInstance()
                        .asDomainObject(dbEntity.getScreeningRoom())
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Screening asDbObject(ScreeningBO domainObj) {
        return null;
    }

    public static EntityAdapter<ScreeningBO, com.capgemini.movies.database.domain.Screening> getInstance() {
        return instance;
    }
}
