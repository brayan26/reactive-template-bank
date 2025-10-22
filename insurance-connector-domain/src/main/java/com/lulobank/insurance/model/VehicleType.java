package com.lulobank.insurance.model;

public record VehicleType(
    String id,
    String name,
    CostDetails costDetails
) {
}
