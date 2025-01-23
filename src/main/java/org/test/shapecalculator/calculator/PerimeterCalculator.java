package org.test.shapecalculator.calculator;

import lombok.EqualsAndHashCode;

/**
 * The perimeter calculator of geometric shape.
 */
@EqualsAndHashCode(callSuper=false)
class PerimeterCalculator extends GeometricShapeCalculator {

    /**
     * Returns calculated perimeter of the given geometric shape.
     */
    @Override
    public Double handle(GeometricShape shape) {
        return shape.perimeter();
    }
}
