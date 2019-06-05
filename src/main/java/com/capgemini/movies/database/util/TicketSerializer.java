package com.capgemini.movies.database.util;

import com.capgemini.movies.domain.Screening;
import com.capgemini.movies.domain.Ticket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TicketSerializer extends JsonSerializer<Ticket> {

    CustomDateConverter dtConverter = new CustomDateConverter();

    @Override
    public void serialize(Ticket ticket, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("ticketNumber", ticket.getTicketNumber());
        jsonGenerator.writeStringField("orderDate",
                dtConverter.toGraphProperty(ticket.getOrderDate()));
        jsonGenerator.writeNumberField("price", ticket.getPrice());
        jsonGenerator.writeObjectFieldStart("bookedPlace");
        if (ticket.getBookedPlace() != null) {
            jsonGenerator.writeStringField("seatNumber",
                    ticket.getBookedPlace().getSeatNumber());
        }
        if (ticket.getBookedPlace() != null
                && ticket.getBookedPlace().getScreeningRoom() != null) {
            jsonGenerator.writeNumberField("screeningRoom",
                    ticket.getBookedPlace().getScreeningRoom().getEntityId());
        }
        jsonGenerator.writeEndObject();
        Screening scr = ticket.getScreening();
        jsonGenerator.writeObjectFieldStart("screening");
        jsonGenerator.writeNumberField("entityId", scr.getEntityId());
        jsonGenerator.writeNumberField("movie", scr.getEntityId());
        jsonGenerator.writeStringField("screeningDate",
                dtConverter.toGraphProperty(scr.getScreeningDate()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
