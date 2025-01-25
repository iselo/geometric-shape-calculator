package org.test.shapecalculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CalculatorController {

    private final CalculatorService service;

    @Autowired
    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    /**
     * Handles a web request to the REST endpoint `/{measurement}/{shape}`.
     * <p>
     * The `{measurement}` and `/`{shape}` are variable parameters
     * to define what measurement operation should be processed on which
     * shape type.
     * <p>
     * Current implementation supports area and perimeter calculation for
     * a square, circle, rectangle and triangle.
     *
     * @param measurement the name of the geometry shape measurement type
     * @param shape       the geometry shape deserializer
     * @param json        the json data that describes geometry shape type details
     * @return calculated result of the geometric shape measurement
     */
    @PostMapping(path = "/{measurement}/{shape}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CalculatorResult calculate(@PathVariable("measurement") Measurement measurement,
                                      @PathVariable("shape") Shape shape,
                                      @RequestBody String json) {
        var shapeInstance = shape.newGeometricShape(json);
        var calculator = measurement.calculator();
        return service.getMeasurement(calculator, shapeInstance);
    }
}
