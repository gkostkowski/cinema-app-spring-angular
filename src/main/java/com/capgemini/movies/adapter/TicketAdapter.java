package com.capgemini.movies.adapter;

import com.capgemini.movies.database.domain.Screening;
import com.capgemini.movies.database.domain.Seat;
import com.capgemini.movies.domain.ScreeningBO;
import com.capgemini.movies.domain.SeatBO;
import com.capgemini.movies.domain.TicketBO;

public class TicketAdapter implements EntityAdapter<TicketBO, com.capgemini.movies.database.domain.Ticket> {
    private static TicketAdapter instance = new TicketAdapter();

    private TicketAdapter() {
    }

    @Override
    public TicketBO asDomainObject(com.capgemini.movies.database.domain.Ticket dbEntity) {
        Screening dbScreening = dbEntity.getScreening();
        ScreeningBO screening = null;
        if (dbScreening != null) {
            screening = ScreeningAdapter.getInstance().asDomainObject(dbScreening);
        }
        Seat dbSeat = dbEntity.getBookedPlace();
        SeatBO seat = null;
        if (dbSeat != null) {
            seat = SeatAdapter.getInstance().asDomainObject(dbSeat);
        }

        return new TicketBO(
                dbEntity.getTicketNumber(),
                dbEntity.getOrderDate(),
                screening,
                seat,
                dbEntity.getPrice()
        );
    }

    @Override
    public com.capgemini.movies.database.domain.Ticket asDbObject(TicketBO domainObj) {
        ScreeningBO screening = domainObj.getScreening();
        Screening dbScreening = null;
        if (screening != null) {
            dbScreening = ScreeningAdapter.getInstance().asDbObject(screening);
        }
        SeatBO seat = domainObj.getSeat();
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
