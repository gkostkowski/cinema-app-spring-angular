package com.capgemini.movies.rest.exception;

public class ReservationAfterScreeningException extends Throwable {

    private static final String MSG = "Cannot make a reservation for screening " +
            "which already took place.";

    public ReservationAfterScreeningException() {
        super(MSG);
    }
}
