package com.itheima.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LenientLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString ==  null || valueAsString.isEmpty()){
            return null;
        }
        try {
            return LocalDate.parse(valueAsString);
        } catch (Exception e) {
//            return LocalDateTime.parse(valueAsString, ISO_LOCAL_DATE_TIME).toLocalDate();
            return ZonedDateTime.parse(valueAsString).toLocalDate();
        }
    }
}
