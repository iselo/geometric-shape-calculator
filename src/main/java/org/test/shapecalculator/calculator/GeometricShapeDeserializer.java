package org.test.shapecalculator.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The deserializer of geometric shape from JSON string to the object.
 */
final class GeometricShapeDeserializer implements Deserializer<GeometricShape> {

    private final Class<? extends GeometricShape> type;

    public GeometricShapeDeserializer(Class<? extends GeometricShape> type) {
        this.type = checkNotNull(type);
    }

    @Override
    public GeometricShape fromJson(String json) throws JsonSyntaxException {
        checkNotNull(json);
        return new Gson().fromJson(json, type);
    }
}
