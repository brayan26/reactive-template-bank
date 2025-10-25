package com.lulobank.insurance.model;

public record AdditionalProduct(
        String type,
        boolean include,
        boolean available,
        String planId,
        boolean hasIndependedTotalValue,
        Quote quote
) {
}
