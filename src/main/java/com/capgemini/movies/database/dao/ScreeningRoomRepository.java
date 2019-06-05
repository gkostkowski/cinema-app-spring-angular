package com.capgemini.movies.database.dao;

import com.capgemini.movies.domain.ScreeningRoom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScreeningRoomRepository extends CrudRepository<ScreeningRoom, Long> {

    @Query("match (scrRoom:ScreeningRoom)<-[:SHOWED_IN]-(:Screening {entityId:{0} }) return scrRoom;")
    ScreeningRoom findByScreeningRoomId(@Param("screeningId") long screeningId);
}
