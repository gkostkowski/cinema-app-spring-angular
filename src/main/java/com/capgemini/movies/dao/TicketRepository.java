package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.Movie;
import com.capgemini.movies.database.domain.Screening;
import com.capgemini.movies.database.domain.Seat;
import com.capgemini.movies.database.domain.Ticket;
import org.joda.time.LocalDateTime;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findAll();

    Ticket findByTicketNumber(String ticketNumber);

    @Query("match (g:Genre)<-[r1:HAS_GENRE]-(m:Movie)<-[r2:SHOWS]-(s:Screening) return m, g, s, r1, r2;")
    List<Movie> findAllFull();

    @Depth(value = 2)
    @Query("match (s:Screening {entityId:{1}})-[:SHOWED_IN]->(scR:ScreeningRoom)" +
            "-[:HAS]->(seat:Seat {seatNumber:{2}}) " +
            "with s, seat " +
            "where not exists ((s)<-[:BOUGHT_FOR]-(:Ticket)-[:WITH_BOOKED_PLACE]->(seat)) " +
            "create (s)<-[:BOUGHT_FOR]-" +
            "(t:Ticket {ticketNumber:{0}, orderDate:{3}, price:{4} })" +
            "-[:WITH_BOOKED_PLACE]->(seat)")
    Ticket addTicket(@Param("ticketNo") String ticketNo,
                   @Param("screening") long screening,
                   @Param("place") String place,
                   @Param("orderDt") String orderDt,
                   @Param("price") Double price);
}
