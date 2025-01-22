package org.test.shapecalculator.calculator;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The exception is risen when requested measurement is unsupported by
 * the calculator.
 */
@ResponseBody
public final class NotSupportedMeasurementException extends RuntimeException {

    public NotSupportedMeasurementException(String message) {
        super(message);
    }
}
