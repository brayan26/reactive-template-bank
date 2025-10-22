package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BusqoErrorResponse(
        @JsonProperty("Message")
        String message,
        @JsonProperty("EndDate")
        String endDate
) {
}
