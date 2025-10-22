package com.lulobank.insurance.model;

import lombok.Builder;

public record SoatOfferLulo(
        String idClient,
        String providerQuoteId,
        ClientInformation clientInformation,
        VehicleInformation vehicleInformation
) {
    @Builder
    public static SoatOfferLulo create(String idClient,
                                       String providerQuoteId,
                                       ClientInformation clientInformation,
                                       VehicleInformation vehicleInformation){
        return new SoatOfferLulo(idClient, providerQuoteId, clientInformation, vehicleInformation);
    }
}
