package org.test.enumfactory;

import com.google.gson.Gson;

import static com.google.common.base.Preconditions.checkNotNull;

public enum Shape implements EnumEntity {

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
    public GeometricShape newShape(String json) {
        checkNotNull(json);
        return gson.fromJson(json, type);
    }
}
