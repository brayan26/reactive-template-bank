package com.lulobank.insurance.model;

import lombok.Builder;

import java.math.BigDecimal;

public record Quote(CostDetails costDetails, BigDecimal coverageAmount, BigDecimal bountyValue, BigDecimal appliedTax,
                    BigDecimal taxValue) {

    @Builder
    public static Quote create(final CostDetails costDetails, final BigDecimal coverageAmount, final BigDecimal bountyValue,
                               final BigDecimal appliedTax, final BigDecimal taxValue) {
        return new Quote(costDetails, coverageAmount, bountyValue, appliedTax, taxValue);
    }
}
