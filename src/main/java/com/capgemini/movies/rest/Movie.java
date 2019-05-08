package com.capgemini.movies.rest;

import java.util.Objects;

public class Movie {
    private String description;
    private Long id;
    private String title;
    private String directing;
    private int productionYear;
    private MovieGenre genre;

    public Movie(Long id, String title, String directing, String description, int
                 productionYear, MovieGenre genre) {
        this.id = id;
        this.title = title;
        this.directing = directing;
        this.description = description;
        this.productionYear = productionYear;
        this.genre = genre;
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

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
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
        Movie movie = (Movie) o;
        return productionYear == movie.productionYear &&
                Objects.equals(description, movie.description) &&
                Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(directing, movie.directing) &&
                genre == movie.genre;
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, id, title, directing, productionYear, genre);
    }
}
