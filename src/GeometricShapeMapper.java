package org.test.shapecalculator.calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class GeometricShapeMapper {

    private ObjectMapper objectMapper;

    public GeometricShapeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T toObject(String json, Class<T> type) {
        return new Gson().fromJson(json, type);
/*
    !!! Possibly bug "single field parameter"
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to deserialize Json", e);
        }
*/
    }

    public <T> String toJson(T instance) {
        try {
            return objectMapper.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize Json" + e.getMessage());
        }
    }
}
