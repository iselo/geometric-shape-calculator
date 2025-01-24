package org.test.shapecalculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Area calculator")
class AreaCalculatorTest {

    private AreaCalculator calculator;


    @BeforeEach
    void setUp() {
        calculator = new AreaCalculator();
    }
    @ParameterizedTest
    @MethodSource("shapesAndResults")
    @DisplayName("can measure shape")
    void measuresGeometricShape(GeometricShape shape, Double expectedResult) {
        assertThat(calculator.calculate(shape))
                .isCloseTo(expectedResult, within(0.0001));
    }

    private static Stream<Arguments> shapesAndResults() {
        return Stream.of(
                Arguments.of(new Square(2.1), 4.41),
                Arguments.of(new Rectangle(2.0, 4.5), 9.0),
                Arguments.of(new Circle(1.0), 3.1416),
                Arguments.of(new Circle(2.1), 13.8544),
                Arguments.of(new Triangle(4.0, 3.0, 5.0, 3.0), 7.5)
        );
    }
}
