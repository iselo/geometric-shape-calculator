package org.test.enumfactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The rectangle geometric shape.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
final class Rectangle implements GeometricShape {

    @NotNull
    @Positive
    private final Double sideA;

    @NotNull
    @Positive
    private final Double sideB;

    @Override
    public Double accept(GeometricShapeVisitor visitor) {
        return visitor.calculate(this);
    }
}
