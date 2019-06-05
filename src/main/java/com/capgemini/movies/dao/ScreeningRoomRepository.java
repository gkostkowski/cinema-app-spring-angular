package com.capgemini.movies.dao;

import com.capgemini.movies.database.domain.ScreeningRoom;
import com.capgemini.movies.database.domain.Seat;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRoomRepository extends CrudRepository<ScreeningRoom, Long> {

    @Query("match (scrRoom:ScreeningRoom)<-[:SHOWED_IN]-(:Screening {entityId:{0} }) return scrRoom;")
    ScreeningRoom findByScreeningRoomId(@Param("screeningId") long screeningId);
}
