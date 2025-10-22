package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Quote(
        @JsonProperty("CoverageAmount")
        BigDecimal coverageAmount,
        @JsonProperty("BountyValue")
        BigDecimal bountyValue,
        @JsonProperty("TotalValue")
        BigDecimal totalValue,
        @JsonProperty("AppliedTax")
        BigDecimal appliedTax,
        @JsonProperty("TaxValue")
        BigDecimal taxValue
) {
}
