package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A triangle geometric shape.
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Triangle implements GeometricShape {

    @NotNull
    @Positive
    private final Double sideA;

    @NotNull
    @Positive
    private final Double sideB;

    @NotNull
    @Positive
    private final Double sideC;

    @NotNull
    @Positive
    private final Double height;

    @Override
    public Double area() {
        return sideC * height / 2;
    }

    @Override
    public Double perimeter() {
        return sideA + sideB + sideC;
    }
}
