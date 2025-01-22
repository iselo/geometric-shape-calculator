package org.test.shapecalculator;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The class represents geometric shape calculator measurement status for
 * the requests to which the application throws an exception.
 */
@ResponseBody
@AllArgsConstructor
@Getter
public final class CalculatorMeasurementStatus {

    @NotNull
    private String message;
}
