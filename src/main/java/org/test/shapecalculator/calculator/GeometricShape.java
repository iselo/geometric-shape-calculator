package org.test.shapecalculator.calculator;

/**
 * Describes geometric shape measurement operations.
 */
interface GeometricShape {

    Double accept(GeometricShapeVisitor visitor);
}
