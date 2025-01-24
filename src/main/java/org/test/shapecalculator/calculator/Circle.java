package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The circle geometric shape.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
final class Circle implements GeometricShape {

    @NotNull
    @Positive
    private final Double radius;

    @Override
    public Double accept(GeometricShapeVisitor visitor) {

        return visitor.calculate(this);
    }
}
