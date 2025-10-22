package com.lulobank.insurance.model;

import java.util.List;

public record VehicleInformation(
        String plate,
        String model,
        String brand,
        String serviceTypeId,
        String serviceTypeName,
        String vehicleTypeId,
        String passengers,
        String actualSOATDueDate,
        String startDate,
        String endDate,
        List<VehicleType> vehicleType,
        List<AdditionalProduct> additionalProducts
) {
}
