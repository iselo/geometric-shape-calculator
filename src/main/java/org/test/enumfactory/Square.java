package org.test.enumfactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The square geometric shape.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
final class Square implements GeometricShape {

    @NotNull
    @Positive
    private Double side;

    @Override
    public Double accept(GeometricShapeVisitor visitor) {
        return visitor.calculate(this);
    }
}
