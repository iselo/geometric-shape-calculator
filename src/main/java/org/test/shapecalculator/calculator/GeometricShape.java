package org.test.shapecalculator.calculator;

/**
 * Describes geometric shape measurement operations.
 */
interface GeometricShape {

    /**
     * Accepts geometric shape visitor to perform measurement operation.
     */
    Double accept(GeometricShapeVisitor visitor);
}
