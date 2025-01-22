package org.test.shapecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The application that provides web service for calculating the area and
 * perimeter of geometric shapes.
 * <p>
 * The application is implemented as a REST API with current set of supported
 * shapes that include a square, rectangle, triangle, and circle.
 */
@SpringBootApplication
public class GeometricShapeCalculatorApplication {

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        SpringApplication.run(GeometricShapeCalculatorApplication.class, args);
    }

}
