package com.ncherkas.ecom.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by nazariycherkas on 12/3/14.
 */
public class OptionalStringDeserializer extends JsonDeserializer<Optional<String>> {

    @Override
    public Optional<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        return Optional.ofNullable(jsonParser.getValueAsString());
    }
}
