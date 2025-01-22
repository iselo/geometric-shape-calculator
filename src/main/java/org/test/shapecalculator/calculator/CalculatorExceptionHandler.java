package org.test.shapecalculator.calculator;

import com.google.gson.JsonSyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.shapecalculator.AbstractExceptionHandler;
import org.test.shapecalculator.CalculatorMeasurementStatus;

/**
 * Handles exceptional states of the geometric shape calculator and provides
 * a web responses with message described by JSON content.
 */
@RestControllerAdvice
final class CalculatorExceptionHandler extends AbstractExceptionHandler {

    /**
     * Handles situations when requested geometric shape type is not supported
     * by the API.
     */
    @ExceptionHandler(NotSupportedShapeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CalculatorMeasurementStatus> handle(HttpServletRequest request,
                                                              NotSupportedShapeException e) {
        return newResponseEntity(request, e, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles situations when requested geometric shape measurement type is not
     * supported by the API.
     */
    @ExceptionHandler(NotSupportedMeasurementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CalculatorMeasurementStatus> handle(HttpServletRequest request,
                                                              NotSupportedMeasurementException e) {
        return newResponseEntity(request, e, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles situations when provided geometric shape details are violates
     * its constraints.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CalculatorMeasurementStatus> handle(HttpServletRequest request,
                                                              ConstraintViolationException e) {
        return newResponseEntity(request, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonSyntaxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CalculatorMeasurementStatus> handle(HttpServletRequest request,
                                                              JsonSyntaxException e) {
        return newResponseEntity(request, e, HttpStatus.BAD_REQUEST);
    }
}
