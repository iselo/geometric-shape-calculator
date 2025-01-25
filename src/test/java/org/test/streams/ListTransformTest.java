package org.test.streams;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ListTransformTest {

    private List<PostAddress> input;
    private final TypeReference<List<PostAddress>> inputTypeReference = new TypeReference<>() {
    };
    private Map<String, Map<String, List<PostAddress>>> output;
    private final TypeReference<Map<String, Map<String, List<PostAddress>>>> outputTypeReference = new TypeReference<>() {
    };

    @BeforeEach
    void setUp() {
        assertThatNoException().isThrownBy(
                () -> input = loadFromJson("/data/addresses.json", inputTypeReference)
        );
        assertThatNoException().isThrownBy(
                () -> output = loadFromJson("/data/countries.json", outputTypeReference)
        );
    }

    @Test
    void hasValidInput() {
        assertThat(input.size()).isEqualTo(5);
    }

    @Test
    void hasValidOutput() {
        assertThat(output.size()).isEqualTo(2);
    }

    @Test
    void canTransform() {
        var postCountryMap = input.stream()
                .collect(Collectors.groupingBy(PostAddress::getCountry,
                        Collectors.groupingBy(PostAddress::getRegion)));

        assertThat(postCountryMap).isEqualTo(output);
    }

    private <T> T loadFromJson(String resource, TypeReference<T> type) {
        try {
            var inputStream = TypeReference.class.getResourceAsStream(resource);
            return new ObjectMapper().readValue(inputStream, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    }

}
