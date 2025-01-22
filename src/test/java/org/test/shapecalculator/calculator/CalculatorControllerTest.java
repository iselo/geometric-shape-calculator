package org.test.shapecalculator.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CalculatorService service;

    @ParameterizedTest
    @MethodSource
    @DisplayName("obtains response with calculator results")
    void obtainsCalculatorResultResponse(String measurementType,
                                         String shapeType,
                                         GeometricShapeCalculator calculator,
                                         GeometricShape shape,
                                         Double expectedValue) throws Exception {
        var urlTemplate = format("/%s/%s", measurementType, shapeType);
        perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(shape))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.value").value(closeTo(expectedValue, 0.001)));

        verify(service).getMeasurement(calculator, shape);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("handles exception of unsupported features")
    void returnsUnsupportedErrorMessageAndHttp404(String urlTemplate,
                                                  Class<? extends Throwable> expectedExceptionType,
                                                  String expectedMessage) throws Exception {
        perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"side\":2.0}")
        )
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertThat(result.getResolvedException())
                                .isInstanceOf(expectedExceptionType))
                .andExpect(jsonPath("$.message").value(startsWith(expectedMessage)));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("handles exception of JSON syntax")
    void returnsJsonValidationErrorAnd400(String urlTemplate, Class<? extends GeometricShape> shape) throws Exception {
        perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"vector\":}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertThat(result.getResolvedException())
                                .isInstanceOf(JsonSyntaxException.class))
                .andExpect(jsonPath("$.message").value(startsWith("com.google.gson.stream.MalformedJsonException")));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("handles exception of constrains violations")
    void returnsConstraintsViolationsErrorAndHttp400(String urlTemplate, GeometricShape shape) throws Exception {
        perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(shape))
        )
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertThat(result.getResolvedException())
                                .isInstanceOf(ConstraintViolationException.class))
                .andExpect(jsonPath("$.message").value(startsWith("Geometric shape violates:")));
    }

    private static Stream<Arguments> obtainsCalculatorResultResponse() {
        return Stream.of(
                Arguments.of("area", "square", new AreaCalculator(), new Square(2.1), 4.41),
                Arguments.of("area", "rectangle", new AreaCalculator(), new Rectangle(2.0, 4.5), 9.0),
                Arguments.of("area", "circle", new AreaCalculator(), new Circle(1.0), 3.1416),
                Arguments.of("area", "circle", new AreaCalculator(), new Circle(2.1), 13.8544),
                Arguments.of("area", "triangle", new AreaCalculator(), new Triangle(4.0, 3.0, 5.0, 3.0), 7.5),
                Arguments.of("perimeter", "square", new PerimeterCalculator(), new Square(2.1), 8.4),
                Arguments.of("perimeter", "rectangle", new PerimeterCalculator(), new Rectangle(2.0, 4.5), 13.0),
                Arguments.of("perimeter", "circle", new PerimeterCalculator(), new Circle(1.0), 6.2832),
                Arguments.of("perimeter", "circle", new PerimeterCalculator(), new Circle(2.1), 13.1946),
                Arguments.of("perimeter", "triangle", new PerimeterCalculator(), new Triangle(4.0, 3.0, 5.0, 3.0), 12.0)
        );
    }

    private static Stream<Arguments> returnsUnsupportedErrorMessageAndHttp404() {
        return Stream.of(
                Arguments.of("/area/unsupportedShape", NotSupportedShapeException.class, "Requested geometric shape is not supported: unsupportedShape"),
                Arguments.of("/perimeter/unsupportedShape", NotSupportedShapeException.class, "Requested geometric shape is not supported: unsupportedShape"),
                Arguments.of("/unsupportedMeasurement/square", NotSupportedMeasurementException.class, "Requested geometric shape measurement is not supported: unsupportedMeasurement"),
                Arguments.of("/unsupportedMeasurement/circle", NotSupportedMeasurementException.class, "Requested geometric shape measurement is not supported: unsupportedMeasurement"),
                Arguments.of("/unsupportedMeasurement/rectangle", NotSupportedMeasurementException.class, "Requested geometric shape measurement is not supported: unsupportedMeasurement"),
                Arguments.of("/unsupportedMeasurement/triangle", NotSupportedMeasurementException.class, "Requested geometric shape measurement is not supported: unsupportedMeasurement")
        );
    }

    private static Stream<Arguments> returnsJsonValidationErrorAnd400() {
        return Stream.of(
                Arguments.of("/area/square", Square.class),
                Arguments.of("/area/square", Rectangle.class),
                Arguments.of("/area/circle", Circle.class),
                Arguments.of("/area/triangle", Triangle.class),
                Arguments.of("/perimeter/square", Square.class),
                Arguments.of("/perimeter/rectangle", Rectangle.class),
                Arguments.of("/perimeter/circle", Circle.class),
                Arguments.of("/perimeter/triangle", Triangle.class)
        );
    }


    private static Stream<Arguments> returnsConstraintsViolationsErrorAndHttp400() {
        return Stream.of(
                Arguments.of("/area/square", new Square(null)),
                Arguments.of("/area/square", new Square(0.0)),
                Arguments.of("/area/square", new Square(-2.1)),
                Arguments.of("/area/circle", new Circle(null)),
                Arguments.of("/area/circle", new Circle(0.0)),
                Arguments.of("/area/circle", new Circle(-10.0)),
                Arguments.of("/area/rectangle", new Rectangle(null, 4.5)),
                Arguments.of("/area/rectangle", new Rectangle(2.0, null)),
                Arguments.of("/area/rectangle", new Rectangle(-2.0, 4.5)),
                Arguments.of("/area/rectangle", new Rectangle(2.0, -4.5)),
                Arguments.of("/area/rectangle", new Rectangle(0.0, 4.5)),
                Arguments.of("/area/rectangle", new Rectangle(2.0, 0.0)),
                Arguments.of("/area/triangle", new Triangle(null, 1.0, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(0.0, 1.0, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(-1.0, 1.0, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, null, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 0.0, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, -2.0, 1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 1.0, null, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 1.0, 0.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 2.0, -1.0, 1.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 1.0, 1.0, null)),
                Arguments.of("/area/triangle", new Triangle(1.0, 1.0, 1.0, 0.0)),
                Arguments.of("/area/triangle", new Triangle(1.0, 2.0, 1.0, -1.0))
                );
    }

    private ResultActions perform(RequestBuilder operationToTest) throws Exception {
        return mockMvc.perform(operationToTest);
    }
}