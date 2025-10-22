package com.lulobank.insurance.model.busqo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BusqoRequest(
        @JsonProperty("Plate")
        String plate,
        @JsonProperty("Identification")
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
