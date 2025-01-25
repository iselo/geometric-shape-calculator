package org.test.streams;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
final class PostAddress {
    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("city")
    private String city;

    @JsonProperty("address")
    private String address;
}
