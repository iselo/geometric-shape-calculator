package org.test.shapecalculator.calculator;

import com.google.gson.Gson;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The geometric shape deserializer.
 */
enum Shape {

    square(Square.class),
    circle(Circle.class),
    rectangle(Rectangle.class),
    triangle(Triangle.class);

    private final Class<? extends GeometricShape> type;
    private final Gson gson = new Gson();

    Shape(Class<? extends GeometricShape> type) {
        this.type = type;
    }

    /**
     * Returns a new object deserialized from given string.
     */
    public GeometricShape newGeometricShape(String json) {
        checkNotNull(json);
        return gson.fromJson(json, type);
    }
}
