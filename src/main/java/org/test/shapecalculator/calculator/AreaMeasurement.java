package org.test.shapecalculator.calculator;

import static java.lang.Math.PI;

/**
 * The area measurement operation on geometric shape type.
 */
final class AreaMeasurement implements GeometricShapeVisitor {

    /**
     * Returns calculated area for the square geometric shape.
     */
    @Override
    public Double calculate(Square shape) {
        var side = shape.getSide();
        return side * side;
    }

    /**
     * Returns calculated area for the square geometric shape.
     */
    @Override
    public Double calculate(Circle shape) {
        var radius = shape.getRadius();
        return PI * radius * radius;
    }

    /**
     * Returns calculated area for the square geometric shape.
     */
    @Override
    public Double calculate(Rectangle shape) {
        return shape.getSideA() * shape.getSideB();
    }

    /**
     * Returns calculated area for the triangle geometric shape.
     */
    @Override
    public Double calculate(Triangle shape) {
        return shape.getSideC() * shape.getHeight() / 2;
    }
}
