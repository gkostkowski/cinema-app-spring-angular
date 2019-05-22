package com.capgemini.movies.adapter;

import com.capgemini.movies.database.domain.Genre;
import com.capgemini.movies.domain.Movie;
import com.capgemini.movies.domain.MovieGenre;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieAdapter implements EntityAdapter<Movie, com.capgemini.movies.database.domain.Movie> {

    private static final MovieAdapter instance = new MovieAdapter();

    @Override
    public Movie asDomainObject(com.capgemini.movies.database.domain.Movie dbEntity) {
        Set<Genre> dbGenres = dbEntity.getGenres();
        Set<MovieGenre> genres = null;
        if (dbGenres != null) {
            genres = dbGenres.stream()
                    .map(g -> new MovieGenre(g.getName()))
                    .collect(Collectors.toSet());
        }
        return new Movie(
                dbEntity.getEntityId(),
                dbEntity.getTitle(),
                dbEntity.getDirecting(),
                dbEntity.getDescription(),
                dbEntity.getProductionYear(),
                genres
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Movie asDbObject(Movie domainObj) {
        throw new NotImplementedException();
    }

    public static EntityAdapter<Movie, com.capgemini.movies.database.domain.Movie> getInstance() {
        return instance;
    }
}
