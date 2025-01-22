package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A rectangle geometric shape.
 */
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Rectangle implements GeometricShape {

    @NotNull
    @Positive
    private final Double sideA;

    @NotNull
    @Positive
    private final Double sideB;

    @Override
    public Double area() {
        return sideA * sideB;
    }

    @Override
    public Double perimeter() {
        return 2 * (sideA + sideB);
    }
}
