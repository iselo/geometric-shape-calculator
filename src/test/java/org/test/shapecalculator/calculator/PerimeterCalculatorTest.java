package org.test.shapecalculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Perimeter calculator")
@AutoConfigureWebTestClient
class PerimeterCalculatorTest {

    private PerimeterCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PerimeterCalculator();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("can measure shape")
    void measuresGeometricShape(GeometricShape shape, Double expectedResult) {
        assertThat(calculator.calculate(shape))
                .isCloseTo(expectedResult, within(0.0001));
    }

    private static Stream<Arguments> measuresGeometricShape() {
        return Stream.of(
                Arguments.of(new Square(2.1), 8.4),
                Arguments.of(new Rectangle(2.0, 4.5), 13.0),
                Arguments.of(new Circle(1.0), 6.2832),
                Arguments.of(new Circle(2.1), 13.1946),
                Arguments.of(new Triangle(4.0, 3.0, 5.0, 3.0), 12.0)
        );
    }
}
