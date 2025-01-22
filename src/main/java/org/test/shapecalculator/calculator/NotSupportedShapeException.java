package org.test.shapecalculator.calculator;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The exception is risen when requested geometric shape is unsupported by
 * the calculator.
 */
@ResponseBody
public final class NotSupportedShapeException extends RuntimeException {

    public NotSupportedShapeException(String message) {
        super(message);
    }
}
