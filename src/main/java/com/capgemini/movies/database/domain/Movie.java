package com.capgemini.movies.database.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Movie extends Entity {

    public Movie() {
        genres = new HashSet<>();
    }

    @JsonProperty("entityId")
    Long entityId;

    @JsonProperty("title")
    String title;

    @JsonProperty("directing")
    String directing;

    @JsonProperty("description")
    String description;

    @JsonProperty("productionYear")
    Integer productionYear;

    @JsonProperty("genre")
    @Relationship(type = "HAS_GENRE", direction = Relationship.OUTGOING)
    Set<Genre> genres;

    @Relationship(type = "SHOWS", direction = Relationship.INCOMING)
    Set<Screening> screenings;


    public Movie(Long id, String title, String directing, String description, int
            productionYear, Set<Genre> genre) {
        this.entityId = id;
        this.title = title;
        this.directing = directing;
        this.description = description;
        this.productionYear = productionYear;
        this.genres = genre;
    }

    @Override
    public String toString() {
        return String.format("Movie(id=%d, '%s', '%s', %d, %s)", entityId, title,
                directing,
                productionYear,
                genres.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","))
                );
    }

    public String getTitle() {
        return title;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getDirecting() {
        return directing;
    }

    public String getDescription() {
        return description;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }
}
