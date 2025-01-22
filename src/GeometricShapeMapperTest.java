package org.test.shapecalculator.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Deprecated
class GeometricShapeMapperTest {

    private GeometricShapeMapper serializer;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        serializer = new GeometricShapeMapper(objectMapper);
    }

    @ParameterizedTest
    @MethodSource
    void serializesJson(GeometricShape shape, String expectedJson) {
        assertThat(serializer.toJson(shape))
                .isEqualTo(expectedJson);
    }

    @ParameterizedTest
    @MethodSource
    void deserializesJson(String json, Class<? extends GeometricShape> type, GeometricShape expectedShape) {
        assertThat(serializer.toObject(json, type))
                .isEqualTo(expectedShape);
    }

    private static Stream<Arguments> serializesJson() {
        return Stream.of(
                Arguments.of(new Square(2.0), "{\"side\":2.0}"),
                Arguments.of(new Circle(1.0), "{\"radius\":1.0}"),
                Arguments.of(new Rectangle(1.5, 3.8), "{\"sideA\":1.5,\"sideB\":3.8}")
        );
    }

    private static Stream<Arguments> deserializesJson() {
        return Stream.of(
                Arguments.of("{\"side\":2.0}", Square.class, new Square(2.0)),
                Arguments.of("{\"radius\":1.0}", Circle.class, new Circle(1.0)),
                Arguments.of("{\"sideA\":1.5,\"sideB\":3.8}", Rectangle.class, new Rectangle(1.5, 3.8))
        );
    }
}