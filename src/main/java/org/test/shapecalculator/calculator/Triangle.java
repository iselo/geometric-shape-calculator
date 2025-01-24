package org.test.shapecalculator.calculator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The triangle geometric shape.
 */
@AllArgsConstructor
@Getter
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
    public Double accept(GeometricShapeVisitor visitor) {
        return visitor.visit(this);
    }
}
