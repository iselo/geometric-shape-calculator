package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.lang.Math.PI;

/**
 * The circle geometric shape.
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
final class Circle implements GeometricShape {

    @NotNull
    @Positive
    private final Double radius;

    @Override
    public Double area() {
        return PI * radius * radius;
    }

    @Override
    public Double perimeter() {
        return 2 * PI * radius;
    }
}
