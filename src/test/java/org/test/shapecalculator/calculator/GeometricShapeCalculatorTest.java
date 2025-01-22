package org.test.shapecalculator.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Any geometric shape measurement")
class GeometricShapeCalculatorTest {

    private static Double measurementOfAnyCalculator() {
        return new GeometricShapeCalculator() {
            @Override
            protected Double handle(GeometricShape shape) {
                return 1.0;
            }
        }.calculate(null);
    }

    @Test
    @DisplayName("not accept nulls")
    void throwsNpeOnNullShape() {
        assertThatThrownBy(GeometricShapeCalculatorTest::measurementOfAnyCalculator)
                .isInstanceOf(NullPointerException.class);
    }
}
