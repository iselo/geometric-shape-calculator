package org.test.shapecalculator.calculator;

import static java.lang.Math.PI;

/**
 * The perimeter measurement operation on geometric shape type.
 */
final class PerimeterMeasurement implements GeometricShapeVisitor {

    /**
     * Returns calculated perimeter for the square geometric shape.
     */
    @Override
    public Double calculate(Square shape) {
        return 4 * shape.getSide();
    }

    /**
     * Returns calculated perimeter for the circle geometric shape.
     */
    @Override
    public Double calculate(Circle shape) {
        return 2 * PI * shape.getRadius();
    }

    /**
     * Returns calculated perimeter for the rectangle geometric shape.
     */
    @Override
    public Double calculate(Rectangle shape) {
        return 2 * (shape.getSideA() + shape.getSideB());
    }

    /**
     * Returns calculated perimeter for the triangle geometric shape.
     */
    @Override
    public Double calculate(Triangle shape) {
        return shape.getSideA() + shape.getSideB() + shape.getSideC();
    }
}
