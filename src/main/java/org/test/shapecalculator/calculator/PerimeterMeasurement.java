package org.test.shapecalculator.calculator;

import static java.lang.Math.PI;

final class PerimeterMeasurement implements GeometricShapeVisitor {

    @Override
    public Double visit(Square shape) {
        return 4 * shape.getSide();
    }

    @Override
    public Double visit(Circle shape) {
        return 2 * PI * shape.getRadius();
    }

    @Override
    public Double visit(Rectangle shape) {
        return 2 * (shape.getSideA() + shape.getSideB());
    }

    @Override
    public Double visit(Triangle shape) {
        return shape.getSideA() + shape.getSideB() + shape.getSideC();
    }
}
