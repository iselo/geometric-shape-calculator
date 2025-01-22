package org.test.shapecalculator.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.hamcrest.Matchers.closeTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT15S")
public class CalculatorControllerEndToEndTest {

    @LocalServerPort
    private Integer randomPort;

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @MethodSource
    @DisplayName("can calculate requested measurement of the geometric shape")
    void returnsMeasurementsAndHttp200(String measurementType,
                                       String shapeType,
                                       String json,
                                       Double expectedValue) {

        var testUri = format("/%s/%s", measurementType, shapeType);
        webTestClient.post()
                .uri(testUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.value").value(closeTo(expectedValue, 0.001));
    }

    private static Stream<Arguments> returnsMeasurementsAndHttp200() {
        return Stream.of(
                Arguments.of("area", "square", "{\"side\":2.0}", 4.0),
                Arguments.of("area", "rectangle", "{\"sideA\":1.5,\"sideB\":3.8}", 5.7, 9.0),
                Arguments.of("area", "circle", "{\"radius\":1.0}", 3.1416),
                Arguments.of("area", "circle", "{\"radius\":2.1}", 13.8544),
                Arguments.of("area", "triangle", "{\"sideA\":4.0,\"sideB\":3.0,\"sideC\":5.0,\"height\":3.0}", 7.5),
                Arguments.of("perimeter", "square", "{\"side\":2.1}", 8.4),
                Arguments.of("perimeter", "rectangle", "{\"sideA\":1.5,\"sideB\":3.8}", 10.6),
                Arguments.of("perimeter", "circle", "{\"radius\":1.0}", 6.2832),
                Arguments.of("perimeter", "circle", "{\"radius\":2.1}", 13.1946),
                Arguments.of("perimeter", "triangle", "{\"sideA\":4.0,\"sideB\":3.0,\"sideC\":5.0,\"height\":3.0}", 12.0)
        );
    }
}
