package org.test.shapecalculator.calculator;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Geometric shape deserializer")
class GeometricShapeDeserializerTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("deserializes from Json")
    void deserializesJson(String json, Class<? extends GeometricShape> type, GeometricShape expectedShape) {
        var deserializer = new GeometricShapeDeserializer(type);
        assertThat(deserializer.fromJson(json))
                .isEqualTo(expectedShape);
    }

    @ParameterizedTest
    @ValueSource(classes = {Square.class, Circle.class, Rectangle.class, Triangle.class})
    @DisplayName("rises JSON syntax exception")
    void detectsJsonSyntaxException(Class<? extends GeometricShape> type){
        assertThatThrownBy( ()-> new GeometricShapeDeserializer(type).fromJson("{vector:"))
                .isInstanceOf(JsonSyntaxException.class);
    }

    private static Stream<Arguments> deserializesJson() {
        return Stream.of(
                Arguments.of("{side:2.0}", Square.class, new Square(2.0)),
                Arguments.of("{radius:1.0}", Circle.class, new Circle(1.0)),
                Arguments.of("{sideA:1.5,sideB:3.8}", Rectangle.class, new Rectangle(1.5, 3.8)),
                Arguments.of("{sideA:4.0,sideB:3.0,sideC:5.0,height:3.0}", Triangle.class, new Triangle(4.0, 3.0, 5.0, 3.0))

        );
    }
}
