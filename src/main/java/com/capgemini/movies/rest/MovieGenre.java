package com.capgemini.movies.rest;

public enum MovieGenre {
    COMEDY("comedy"),
    DRAMA("drama"),
    THRILLER("thriller"),
    ACTION("action"),
    ANIMATION("animation"),
    SCI_FI("science-fiction"),
    OTHER("other");

    private final String genre;

    MovieGenre(String genre) {
        this.genre = genre;
    }
}
