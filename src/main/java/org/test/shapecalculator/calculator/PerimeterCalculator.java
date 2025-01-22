package org.test.shapecalculator.calculator;

import lombok.EqualsAndHashCode;

/**
 * Perimeter calculator of geometric shapes.
 */
@EqualsAndHashCode
class PerimeterCalculator extends GeometricShapeCalculator {

    /**
     * Returns calculated perimeter of the given geometric shape.
     */
    @Override
    public Double handle(GeometricShape shape) {
        return shape.perimeter();
    }
}
