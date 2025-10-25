package com.lulobank.insurance.model;

import java.math.BigDecimal;

public record CostDetails(
        BigDecimal totalValue,
        String currency
) {
}
