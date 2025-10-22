package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Subproducts(
        @JsonProperty("Type")
        String type,
        @JsonProperty("Include")
        boolean include,
        @JsonProperty("Available")
        boolean available,
        @JsonProperty("PlanId")
        String planId,
        @JsonProperty("HasIndependedTotalValue")
        boolean hasIndependedTotalValue,
        @JsonProperty("Quote")
        Quote quote
) {
}
