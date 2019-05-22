package com.capgemini.movies.database.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class CustomDateConverter implements AttributeConverter<org.joda.time.LocalDateTime, String> {

    public static final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    @Override
    public String toGraphProperty(LocalDateTime value) {
        return dtFormatter.print(value);
    }

    @Override
    public LocalDateTime toEntityAttribute(String value) {
//        return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return LocalDateTime.parse(value, dtFormatter);
    }
}
