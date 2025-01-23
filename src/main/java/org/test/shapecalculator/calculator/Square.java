package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The square geometric shape.
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
final class Square implements GeometricShape {

    @NotNull
    @Positive
    private Double side;

    @Override
    public Double area() {
        return side * side;
    }

    @Override
    public Double perimeter() {
        return 4 * side;
    }
}
