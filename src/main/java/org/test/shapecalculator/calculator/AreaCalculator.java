package org.test.shapecalculator.calculator;

import lombok.EqualsAndHashCode;

/**
 * Area calculator of geometric shapes.
 */
@EqualsAndHashCode
final class AreaCalculator extends GeometricShapeCalculator {

    /**
     * Returns calculated area of the given geometric shape.
     */
    @Override
    public Double handle(GeometricShape shape) {
        return shape.area();
    }
}
