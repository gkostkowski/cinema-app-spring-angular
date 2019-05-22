package com.capgemini.movies.adapter;

import com.capgemini.movies.database.domain.Screening;
import com.capgemini.movies.database.domain.Seat;
import com.capgemini.movies.domain.Ticket;

public class TicketAdapter implements EntityAdapter<Ticket, com.capgemini.movies.database.domain.Ticket> {
    private static TicketAdapter instance = new TicketAdapter();

    private TicketAdapter() {
    }

    @Override
    public Ticket asDomainObject(com.capgemini.movies.database.domain.Ticket dbEntity) {
        Screening dbScreening = dbEntity.getScreening();
        com.capgemini.movies.domain.Screening screening = null;
        if (dbScreening != null) {
            screening = ScreeningAdapter.getInstance().asDomainObject(dbScreening);
        }
        Seat dbSeat = dbEntity.getBookedPlace();
        com.capgemini.movies.domain.Seat seat = null;
        if (dbSeat != null) {
            seat = SeatAdapter.getInstance().asDomainObject(dbSeat);
        }

        return new Ticket(
                dbEntity.getTicketNumber(),
                dbEntity.getOrderDate(),
                screening,
                seat,
                dbEntity.getPrice()
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Ticket asDbObject(Ticket domainObj) {
        com.capgemini.movies.domain.Screening screening = domainObj.getScreening();
        Screening dbScreening = null;
        if (screening != null) {
            dbScreening = ScreeningAdapter.getInstance().asDbObject(screening);
        }
        com.capgemini.movies.domain.Seat seat = domainObj.getSeat();
        Seat dbSeat = null;
        if (seat != null) {
            dbSeat = SeatAdapter.getInstance().asDbObject(seat);
        }
        return new com.capgemini.movies.database.domain.Ticket(
                domainObj.getTicketNumber(),
                dbScreening,
                domainObj.getOrderDate(),
                dbSeat,
                domainObj.getPrice()
        );
    }

    public static TicketAdapter getInstance() {
        return instance;
    }
}
