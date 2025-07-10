package com.kzree.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Serialization {
    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setAnnotationIntrospector(
                new JacksonAnnotationIntrospector() {
                    @Override
                    public JsonProperty.Access findPropertyAccess(Annotated m) {
                        return null;
                    }
                });
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static byte[] serializeJSONFromObject(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }
}
