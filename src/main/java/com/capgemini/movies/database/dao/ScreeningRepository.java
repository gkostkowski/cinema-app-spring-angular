package com.capgemini.movies.database.dao;

import com.capgemini.movies.domain.Movie;
import com.capgemini.movies.domain.Screening;
import com.capgemini.movies.domain.Seat;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Long> {

    List<Screening> findAll();

    @Query("match (reserved:Seat)<-[r3:WITH_BOOKED_PLACE]-(t:Ticket)-[r4:BOUGHT_FOR]->(s:Screening)-[r1:SHOWED_IN]->(sr:ScreeningRoom)-[r2:HAS]->(seat:Seat) return reserved, t, s, sr, seat, r1, r2, r3, r4;")
    List<Screening> findAllFull();

    List<Screening> findScreeningByMovie(Movie movie);

    List<Screening> findScreeningByMovie_Title(String title);

    @Query("match (s:Seat)<-[:HAS]-(sr:ScreeningRoom)<-[:SHOWED_IN]-(scr:Screening {entityId:{0}}) where not exists((s)<-[:WITH_BOOKED_PLACE]->(:Ticket)-[:BOUGHT_FOR]->(scr)) return s;")
    List<Seat> findFreePlacesForScreening(@Param("entityId") Long entityId);

    @Query("match (s:Screening {entityId:{0}}) with s " +
            "optional match (reserved:Seat)<-[r3:WITH_BOOKED_PLACE]-(t:Ticket)-[r4:BOUGHT_FOR]->(s)-[r1:SHOWED_IN]->(sr:ScreeningRoom)-[r2:HAS]->(seat:Seat) " +
            "optional match (s)-[:SHOWS]->(m:Movie) " +
            "return reserved, t, s, sr, seat, m, r1, r2, r3, r4;")
    Screening findByIdFull(@Param("entityId") Long id);
}
