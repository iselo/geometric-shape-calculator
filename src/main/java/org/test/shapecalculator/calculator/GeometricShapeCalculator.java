package org.test.shapecalculator.calculator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract calculator of geometric shapes.
 */
abstract class GeometricShapeCalculator {

    protected abstract Double handle(GeometricShape shape);

    /**
     * Obtains measurement of the given geometric shape.
     */
    public final Double calculate(GeometricShape shape) {
        checkNotNull(shape);
        return handle(shape);
    }
}