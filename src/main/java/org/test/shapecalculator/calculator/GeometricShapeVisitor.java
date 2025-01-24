package org.test.shapecalculator.calculator;

interface GeometricShapeVisitor {

    Double visit(Square shape);

    Double visit(Circle shape);

    Double visit(Rectangle shape);

    Double visit(Triangle shape);
}
