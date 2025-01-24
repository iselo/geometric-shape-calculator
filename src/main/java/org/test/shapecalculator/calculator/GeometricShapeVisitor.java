package org.test.shapecalculator.calculator;

/**
 * The visitor of the geometric shapes that performs calculation measurement
 * operation.
 */
interface GeometricShapeVisitor {

    /**
     * Obtains calculation for the square geometric shape.
     */
    Double calculate(Square shape);

    /**
     * Obtains calculation for the circle geometric shape.
     */
    Double calculate(Circle shape);

    /**
     * Obtains calculation for the rectangle geometric shape.
     */
    Double calculate(Rectangle shape);

    /**
     * Obtains calculation for the triangle geometric shape.
     */
    Double calculate(Triangle shape);
}
