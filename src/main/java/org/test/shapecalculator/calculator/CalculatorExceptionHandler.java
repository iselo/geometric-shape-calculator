package org.test.shapecalculator.calculator;

import com.google.gson.JsonSyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.shapecalculator.common.AbstractExceptionHandler;
import org.test.shapecalculator.common.ApiErrorResponse;

/**
 * The handler of the exceptional states of the geometric shape calculator.
 * Its methods provide a web response with appropriate HTTP status code and
 * message details.
 */
@RestControllerAdvice
final class CalculatorExceptionHandler extends AbstractExceptionHandler {

    /**
     * Handles situations when requested geometric shape type is not supported
     * by the API.
     */
    @ExceptionHandler(NotSupportedShapeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
                                                   NotSupportedShapeException e) {
        return newResponseEntity(request, e, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles situations when requested geometric shape measurement type is not
     * supported by the API.
     */
    @ExceptionHandler(NotSupportedMeasurementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
                                                   NotSupportedMeasurementException e) {
        return newResponseEntity(request, e, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles situations when provided geometric shape details are violates
     * its constraints.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
                                                   ConstraintViolationException e) {
        return newResponseEntity(request, e, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles situations when provided geometric shape details has incorrect
     * JSON syntax.
     */
    @ExceptionHandler(JsonSyntaxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handle(HttpServletRequest request,
                                                   JsonSyntaxException e) {
        return newResponseEntity(request, e, HttpStatus.BAD_REQUEST);
    }
}
