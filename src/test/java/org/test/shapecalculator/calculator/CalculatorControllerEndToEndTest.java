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
import static org.hamcrest.Matchers.containsString;

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

    @ParameterizedTest
    @MethodSource
    @DisplayName("has an error message on unsupported feature")
    void returnsUnsupportedErrorMessageAndHttp404(String measurementType,
                                                  String shapeType,
                                                  String expectedMessage) {
        var testUri = format("/%s/%s", measurementType, shapeType);
        webTestClient.post()
                .uri(testUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue("{side:2.0}")
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("has an error message on invalid request data")
    void returnsJsonDataErrorMessageAndHttp400(String testUri,
                                               String invalidJsonData,
                                               String expectedMessage) {
        webTestClient.post()
                .uri(testUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidJsonData)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo(expectedMessage);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("has an error message on invalid JSON syntax")
    void returnsJsonSyntaxErrorMessageAndHttp400(String testUri,
                                                 String invalidSyntaxJson,
                                                 String expectedMessage) {
        webTestClient.post()
                .uri(testUri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(invalidSyntaxJson)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").value(containsString(expectedMessage));
    }

    private static Stream<Arguments> returnsMeasurementsAndHttp200() {
        return Stream.of(
                Arguments.of("area", "square", "{side:2.0}", 4.0),
                Arguments.of("area", "rectangle", "{sideA:1.5,sideB:3.8}", 5.7, 9.0),
                Arguments.of("area", "circle", "{radius:1.0}", 3.1416),
                Arguments.of("area", "circle", "{radius:2.1}", 13.8544),
                Arguments.of("area", "triangle", "{sideA:4.0,sideB:3.0,sideC:5.0,height:3.0}", 7.5),
                Arguments.of("perimeter", "square", "{side:2.1}", 8.4),
                Arguments.of("perimeter", "rectangle", "{sideA:1.5,sideB:3.8}", 10.6),
                Arguments.of("perimeter", "circle", "{radius:1.0}", 6.2832),
                Arguments.of("perimeter", "circle", "{radius:2.1}", 13.1946),
                Arguments.of("perimeter", "triangle", "{sideA:4.0,sideB:3.0,sideC:5.0,height:3.0}", 12.0)
        );
    }

    private static Stream<Arguments> returnsUnsupportedErrorMessageAndHttp404() {
        return Stream.of(
                Arguments.of("feature", "square", "Requested geometric shape measurement is not supported: feature"),
                Arguments.of("feature", "circle", "Requested geometric shape measurement is not supported: feature"),
                Arguments.of("feature", "rectangle", "Requested geometric shape measurement is not supported: feature"),
                Arguments.of("feature", "triangle", "Requested geometric shape measurement is not supported: feature"),
                Arguments.of("area", "someshape", "Requested geometric shape is not supported: someshape"),
                Arguments.of("perimeter", "someshape", "Requested geometric shape is not supported: someshape")
        );
    }

    private static Stream<Arguments> returnsJsonDataErrorMessageAndHttp400() {
        return Stream.of(
                Arguments.of("/area/square", "{side: null}", "Geometric shape violates: must not be null"),
                Arguments.of("/area/square", "{side: -1}", "Geometric shape violates: must be greater than 0"),
                Arguments.of("/area/square", "{side: 0}", "Geometric shape violates: must be greater than 0"),
                Arguments.of("/perimeter/square", "{side: null}", "Geometric shape violates: must not be null"),
                Arguments.of("/perimeter/square", "{side: -1}", "Geometric shape violates: must be greater than 0"),
                Arguments.of("/perimeter/square", "{side: 0}", "Geometric shape violates: must be greater than 0")
        );
    }

    private static Stream<Arguments> returnsJsonSyntaxErrorMessageAndHttp400() {
        return Stream.of(
                Arguments.of("/area/square", "{side: null", "EOFException: End of input"),
                Arguments.of("/area/square", "{side:}", "MalformedJsonException: Expected value"),
                Arguments.of("/area/square", "{: 1}", "MalformedJsonException: Expected name"),
                Arguments.of("/area/square", "side: 1}", "IllegalStateException: Expected BEGIN_OBJECT")
        );
    }

}
