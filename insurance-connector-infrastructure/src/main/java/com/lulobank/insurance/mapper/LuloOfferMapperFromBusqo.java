package com.lulobank.insurance.mapper;

import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;
import com.lulobank.insurance.adapters.busqo.response.PersonalInformation;
import com.lulobank.insurance.model.*;

import java.util.List;
import java.util.Optional;

public class LuloOfferMapperFromBusqo {

    private LuloOfferMapperFromBusqo() {

    }

    public static SoatOfferLulo map(BusqoResponse response, String clientId){
        return SoatOfferLulo.builder()
                .idClient(clientId)
                .providerQuoteId(response.quotePublicId())
                .clientInformation(mapClient(response.personalInformation()))
                .vehicleInformation(mapVehicle(response))
                .build();
    }

    private static ClientInformation mapClient(PersonalInformation personalInformation) {
        return new ClientInformation(
                personalInformation.name(),
                personalInformation.lastName(),
                personalInformation.businessName(),
                new Document(personalInformation.identification(), personalInformation.identificationType()),
                personalInformation.email(),
                new Phone(personalInformation.mobilePhone(), null),
                personalInformation.address()
        );
    }

    private static VehicleInformation mapVehicle(BusqoResponse response) {
        return new VehicleInformation(
            response.plate(),
                response.model(),
                response.brand(),
                response.serviceTypeId(),
                response.serviceTypeName(),
                response.vehicleTypeId(),
                response.passengers(),
                response.actualSOATDueDate(),
                response.startDate(),
                response.endDate(),
                Optional.ofNullable(response.homologateResult())
                        .orElse(List.of())
                        .stream().map(homologateResult -> new VehicleType(
                        homologateResult.vehicleTypeId(),
                        homologateResult.vehicleTypeName(),
                        new CostDetails(homologateResult.totalValue(), "COP") //validate currency field
                )).toList(),
                Optional.ofNullable(response.subproducts())
                        .orElse(List.of())
                        .stream().map(subproduct -> new AdditionalProduct(
                        subproduct.type(),
                        subproduct.include(),
                        subproduct.available(),
                        subproduct.planId(),
                        subproduct.hasIndependedTotalValue(),
                        new CostDetails(String.valueOf(subproduct.quote().totalValue()), "")
                )).toList()
        );
    }
}
