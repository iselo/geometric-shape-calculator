package org.test.shapecalculator.common;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The class represents API error response for the requests to which
 * the application throws an exception.
 */
@ResponseBody
@AllArgsConstructor
@Getter
public final class ApiErrorResponse {

    @NotNull
    private String message;
}
