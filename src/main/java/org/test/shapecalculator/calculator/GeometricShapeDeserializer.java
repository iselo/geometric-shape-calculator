package org.test.shapecalculator.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

final class GeometricShapeDeserializer implements Deserializer<GeometricShape> {

    private final Class<? extends GeometricShape> type;

    public GeometricShapeDeserializer(Class<? extends GeometricShape> type) {
        this.type = type;
    }

    @Override
    public GeometricShape fromJson(String json) throws JsonSyntaxException {
        return new Gson().fromJson(json, type);
    }
}
