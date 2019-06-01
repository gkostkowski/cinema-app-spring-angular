package com.capgemini.movies.adapter;

import com.capgemini.movies.database.domain.Genre;
import com.capgemini.movies.domain.MovieBO;
import com.capgemini.movies.domain.MovieGenreBO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieAdapter implements EntityAdapter<MovieBO, com.capgemini.movies.database.domain.Movie> {

    private static final MovieAdapter instance = new MovieAdapter();

    @Override
    public MovieBO asDomainObject(com.capgemini.movies.database.domain.Movie dbEntity) {
        Set<Genre> dbGenres = dbEntity.getGenres();
        Set<MovieGenreBO> genres = null;
        if (dbGenres != null) {
            genres = dbGenres.stream()
                    .map(g -> new MovieGenreBO(g.getName()))
                    .collect(Collectors.toSet());
        }
        return new MovieBO(
                dbEntity.getEntityId(),
                dbEntity.getTitle(),
                dbEntity.getDirecting(),
                dbEntity.getDescription(),
                dbEntity.getProductionYear(),
                genres
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Movie asDbObject(MovieBO domainObj) {
        throw new NotImplementedException();
    }

    public static EntityAdapter<MovieBO, com.capgemini.movies.database.domain.Movie> getInstance() {
        return instance;
    }
}
