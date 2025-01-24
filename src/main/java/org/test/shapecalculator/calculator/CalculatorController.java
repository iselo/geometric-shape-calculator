package org.test.shapecalculator.calculator;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.test.shapecalculator.common.Deserializer;

import java.util.Optional;
import java.util.function.Supplier;

@RestController
class CalculatorController {

    private CalculatorService service;
    private ImmutableMap<String, GeometricShapeVisitor> calculators;
    private ImmutableMap<String, Deserializer<GeometricShape>> deserializers;

    @Autowired
    public CalculatorController(CalculatorService service,
                                ImmutableMap<String, GeometricShapeVisitor> calculators,
                                ImmutableMap<String, Deserializer<GeometricShape>> deserializers) {
        this.service = service;
        this.calculators = calculators;
        this.deserializers = deserializers;
    }

    /**
     * Handles a web request to the REST endpoint `/{measurementType}/{shapeType}`.
     * <p>
     * The `{measurementType}` and `/`{shapeType}` are variable parameters
     * to define what measurement operation should be processed on which
     * shape type. These parameters are case-insensitive.
     * <p>
     * Current implementation supports area and perimeter calculation for
     * a square, circle, rectangle and triangle.
     *
     * @param measurementType the name of the geometry shape measurement type
     * @param shapeType       the name of the geometry shape type
     * @param json            the json data that describes geometry shape type details
     * @return calculated result of the geometric shape measurement
     */
    @PostMapping(path = "/{measurementType}/{shapeType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CalculatorResult calculate(@PathVariable String measurementType,
                                      @PathVariable String shapeType,
                                      @RequestBody String json) {
        var shape = shape(shapeType, json);
        var calculator = calculator(measurementType);
        return service.getMeasurement(calculator, shape);
    }

    private GeometricShapeVisitor calculator(String measurementType) throws NotSupportedMeasurementException {
        var measurementName = measurementType.toLowerCase();
        return Optional.ofNullable(calculators.get(measurementName))
                .orElseThrow(notSupportedMeasurement(measurementType));
    }

    private GeometricShape shape(String shapeType, String json) throws NotSupportedShapeException {
        var shapeName = shapeType.toLowerCase();
        return Optional.ofNullable(deserializers.get(shapeName))
                .orElseThrow(notSupportedShape(shapeType))
                .fromJson(json);
    }

    private Supplier<NotSupportedShapeException> notSupportedShape(String shapeType) {
        return () -> new NotSupportedShapeException("Requested geometric shape is not supported: " + shapeType);
    }

    private Supplier<NotSupportedMeasurementException> notSupportedMeasurement(String measurementType) {
        return () -> new NotSupportedMeasurementException(
                "Requested geometric shape measurement is not supported: " + measurementType);
    }
}
