package com.capgemini.movies.domain;

import java.util.Objects;
import java.util.Set;

public class MovieBO {
    private String description;
    private Long id;
    private String title;
    private String directing;
    private int productionYear;
    private Set<MovieGenreBO> genres;

    public MovieBO(Long id, String title, String directing, String description, int
                 productionYear, Set<MovieGenreBO> genre) {
        this.id = id;
        this.title = title;
        this.directing = directing;
        this.description = description;
        this.productionYear = productionYear;
        this.genres = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirecting() {
        return directing;
    }

    public void setDirecting(String directing) {
        this.directing = directing;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Set<MovieGenreBO> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieBO movie = (MovieBO) o;
        return productionYear == movie.productionYear &&
                Objects.equals(description, movie.description) &&
                Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(directing, movie.directing) &&
                genres == movie.genres;
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, id, title, directing, productionYear, genres);
    }
}
