package com.lulobank.insurance.model.busqo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lulobank.insurance.commons.annotations.Sensitive;

public record BusqoRequest(
        @JsonProperty("Plate")
        String plate,
        @JsonProperty("Identification")
        @Sensitive
        String identification,
        @JsonProperty("IdentificationType")
        String identificationType,
        @JsonProperty("MobilePhone")
        String mobilePhone,
        @JsonProperty("Email")
        String email,
        @JsonProperty("Address")
        String address
) {
}
