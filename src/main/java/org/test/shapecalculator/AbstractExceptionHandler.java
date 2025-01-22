package org.test.shapecalculator;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * An abstract exception handler.
 */
@Slf4j
public abstract class AbstractExceptionHandler {

    protected ResponseEntity<CalculatorMeasurementStatus> newResponseEntity(HttpServletRequest request,
                                                                            Throwable e,
                                                                            HttpStatus httpStatus) {
        log.error(request.getRequestURL().toString() + " | " + e.getMessage() + " | " + httpStatus.toString());
        var measurementError = new CalculatorMeasurementStatus(e.getMessage());
        return new ResponseEntity<>(measurementError, httpStatus);
    }
}
