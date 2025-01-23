package org.test.shapecalculator.calculator;

import lombok.EqualsAndHashCode;

/**
 * The area calculator of geometric shape.
 */
@EqualsAndHashCode(callSuper=false)
final class AreaCalculator extends GeometricShapeCalculator {

    /**
     * Returns calculated area of the given geometric shape.
     */
    @Override
    public Double handle(GeometricShape shape) {
        return shape.area();
    }
}
