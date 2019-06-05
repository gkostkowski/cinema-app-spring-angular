package com.capgemini.movies.database.util;

import com.capgemini.movies.domain.Screening;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ScreeningSerializer extends JsonSerializer<Screening> {

    CustomDateConverter dtConverter = new CustomDateConverter();

    @Override
    public void serialize(Screening scr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeNumberField("entityId", scr.getScreeningRoom().getEntityId());
////        jsonGenerator.writeStringField("seatNumber", scr.getSeatNumber());
//        jsonGenerator.writeObjectFieldStart("movie");
//        jsonGenerator.writeStringField("title", scr.getMovie().getTitle());
//        jsonGenerator.writeEndObject();
//        jsonGenerator.writeStringField("screeningDate",
//                dtConverter.toGraphProperty(scr.getScreeningDate()));
//        jsonGenerator.writeNumberField("screeningRoom",
//                scr.getScreeningRoom().getEntityId());
//        jsonGenerator.writeEndObject();
    }
}
