package com.capgemini.movies.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
//import org.neo4j.ogm.annotation.Version;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public abstract class Entity {

    @JsonProperty("entityId")
    private Long id;

    @JsonProperty
//    @Version
    Long version;

    protected Long getInternalId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || id == null || getClass() != o.getClass()) return false;
        return ((Entity) o).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return (id == null) ? -1 : id.hashCode();
    }
}
