package org.test.shapecalculator.calculator;

import com.google.common.collect.ImmutableMap;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.within;

@SpringBootTest
@DisplayName("Geometric shape calculator service")
class CalculatorServiceTest {

    @MockBean
    private ImmutableMap<String, GeometricShapeVisitor> calculator;

    @Autowired
    private Validator validator;

    private CalculatorService service;

    @BeforeEach
    void setUp() {
        service = new CalculatorService(validator);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("can measure shape")
    void measuresGeometricShape(GeometricShapeVisitor calculator, GeometricShape shape, Double expectedResult) {
        assertThat(service.getMeasurement(calculator, shape).getValue())
                .isCloseTo(expectedResult, within(0.001));
    }

    @ParameterizedTest
    @MethodSource
    void throwsValidationException(GeometricShape shape) {
        assertThatThrownBy(() -> service.getMeasurement(new AreaMeasurement(), shape))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private static Stream<Arguments> measuresGeometricShape() {
        return Stream.of(
                Arguments.of(new AreaMeasurement(), new Square(2.1), 4.41),
                Arguments.of(new AreaMeasurement(), new Rectangle(2.0, 4.5), 9.0),
                Arguments.of(new AreaMeasurement(), new Circle(1.0), 3.1416),
                Arguments.of(new AreaMeasurement(), new Circle(2.1), 13.8544),
                Arguments.of(new AreaMeasurement(), new Triangle(4.0, 3.0, 5.0, 3.0), 7.5),
                Arguments.of(new PerimeterMeasurement(), new Square(2.1), 8.4),
                Arguments.of(new PerimeterMeasurement(), new Rectangle(2.0, 4.5), 13.0),
                Arguments.of(new PerimeterMeasurement(), new Circle(1.0), 6.2832),
                Arguments.of(new PerimeterMeasurement(), new Circle(2.1), 13.1946),
                Arguments.of(new PerimeterMeasurement(), new Triangle(4.0, 3.0, 5.0, 3.0), 12.0)

        );
    }

    private static Stream<Arguments> throwsValidationException() {
        return Stream.of(
                Arguments.of(new Square(null)),
                Arguments.of(new Square(0.0)),
                Arguments.of(new Square(-2.1)),
                Arguments.of(new Circle(null)),
                Arguments.of(new Circle(0.0)),
                Arguments.of(new Circle(-10.0)),
                Arguments.of(new Rectangle(null, 4.5)),
                Arguments.of(new Rectangle(2.0, null)),
                Arguments.of(new Rectangle(-2.0, 4.5)),
                Arguments.of(new Rectangle(2.0, -4.5)),
                Arguments.of(new Rectangle(0.0, 4.5)),
                Arguments.of(new Rectangle(2.0, 0.0)),
                Arguments.of(new Triangle(null, 1.0, 1.0, 1.0)),
                Arguments.of(new Triangle(0.0, 1.0, 1.0, 1.0)),
                Arguments.of(new Triangle(-1.0, 1.0, 1.0, 1.0)),
                Arguments.of(new Triangle(1.0, null, 1.0, 1.0)),
                Arguments.of(new Triangle(1.0, 0.0, 1.0, 1.0)),
                Arguments.of(new Triangle(1.0, -2.0, 1.0, 1.0)),
                Arguments.of(new Triangle(1.0, 1.0, null, 1.0)),
                Arguments.of(new Triangle(1.0, 1.0, 0.0, 1.0)),
                Arguments.of(new Triangle(1.0, 2.0, -1.0, 1.0)),
                Arguments.of(new Triangle(1.0, 1.0, 1.0, null)),
                Arguments.of(new Triangle(1.0, 1.0, 1.0, 0.0)),
                Arguments.of(new Triangle(1.0, 2.0, 1.0, -1.0))
        );
    }
}
