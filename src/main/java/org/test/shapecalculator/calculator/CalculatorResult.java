package org.test.shapecalculator.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The geometry shape calculator result.
 */
@ResponseBody
@AllArgsConstructor
@Getter
@ToString
public class CalculatorResult {

    private final Double value;
}
