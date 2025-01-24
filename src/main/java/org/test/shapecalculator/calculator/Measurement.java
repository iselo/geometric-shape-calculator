package org.test.shapecalculator.calculator;

/**
 * The measurement of the geometric shape.
 */
enum Measurement {

    area(new AreaMeasurement()),
    perimeter(new PerimeterMeasurement());

    private final GeometricShapeVisitor visitor;

    Measurement(GeometricShapeVisitor visitor) {
        this.visitor = visitor;
    }

    /**
     * Returns new measurement calculator.
     */
    public GeometricShapeVisitor calculator(){
        return visitor;
    }
}
