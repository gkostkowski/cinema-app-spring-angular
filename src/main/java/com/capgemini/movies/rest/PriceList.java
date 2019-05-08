package com.capgemini.movies.rest;

public enum PriceList {
    REGULAR(20),
    STUDENT(18),
    CHILD(12);

    public final double price;

    PriceList(double price) {
        this.price = price;
    }

    PriceList() {
        price = 0;
    }

    public double getPrice() {
        return price;
    }
}
