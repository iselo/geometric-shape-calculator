package org.test.shapecalculator.calculator;

import static java.lang.Math.PI;

final class AreaMeasurement implements GeometricShapeVisitor {

    @Override
    public Double visit(Square shape) {
        var side = shape.getSide();
        return side * side;
    }

    @Override
    public Double visit(Circle shape) {
        var radius = shape.getRadius();
        return PI * radius * radius;
    }

    @Override
    public Double visit(Rectangle shape) {
        return shape.getSideA() * shape.getSideB();
    }

    @Override
    public Double visit(Triangle shape) {
        return shape.getSideC() * shape.getHeight() / 2;
    }
}
