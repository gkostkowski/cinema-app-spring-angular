package com.capgemini.movies.database.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre extends Entity {

    @JsonProperty("name")
    private String name;

    public Genre() {
    }

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Genre('%s')", name);
    }
}
