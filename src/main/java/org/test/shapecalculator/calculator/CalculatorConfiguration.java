package org.test.shapecalculator.calculator;

import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CalculatorConfiguration {

    /**
     * Provides a configured calculator instance.
     */
    @Bean
    public ImmutableMap<String, GeometricShapeVisitor> calculator() {
        return ImmutableMap.<String, GeometricShapeVisitor>builder()
                .put("area", new AreaMeasurement())
                .put("perimeter", new PerimeterMeasurement())
                .build();
    }

    /**
     * Provides deserializers for supported geometric shape types.
     */
    @Bean
    public ImmutableMap<String, Deserializer<GeometricShape>> deserializers() {
        return ImmutableMap.<String, Deserializer<GeometricShape>>builder()
                .put("square", new GeometricShapeDeserializer(Square.class))
                .put("circle", new GeometricShapeDeserializer(Circle.class))
                .put("rectangle", new GeometricShapeDeserializer(Rectangle.class))
                .put("triangle", new GeometricShapeDeserializer(Triangle.class))
                .build();
    }
}
