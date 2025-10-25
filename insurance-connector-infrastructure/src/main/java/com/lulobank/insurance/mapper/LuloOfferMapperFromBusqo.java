package com.lulobank.insurance.mapper;

import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;
import com.lulobank.insurance.adapters.busqo.response.PersonalInformation;
import com.lulobank.insurance.adapters.busqo.response.Subproducts;
import com.lulobank.insurance.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LuloOfferMapperFromBusqo {
    private static final String CURRENCY = "COP";

    private LuloOfferMapperFromBusqo() {
    }

    public static SoatOfferLulo map(final BusqoResponse response, final String clientId) {
        return SoatOfferLulo.builder()
                .idClient(clientId)
                .providerQuoteId(response.quotePublicId())
                .clientInformation(mapClient(response.personalInformation()))
                .vehicleInformation(mapVehicle(response))
                .build();
    }

    private static ClientInformation mapClient(final PersonalInformation personalInformation) {
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

    private static VehicleInformation mapVehicle(final BusqoResponse response) {
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
                                new CostDetails(new BigDecimal(homologateResult.totalValue()), CURRENCY)
                        )).toList(),
                Optional.ofNullable(response.subproducts())
                        .orElse(List.of())
                        .stream().map(subproduct -> new AdditionalProduct(
                                subproduct.type(),
                                subproduct.include(),
                                subproduct.available(),
                                subproduct.planId(),
                                subproduct.hasIndependedTotalValue(),
                                mapQuote(subproduct)
                        )).toList()
        );
    }

    private static Quote mapQuote(Subproducts subproduct) {
        if (!subproduct.available()) {
            return null;
        }

        return Quote.builder()
                .costDetails(new CostDetails(subproduct.quote().totalValue(), CURRENCY))
                .coverageAmount(subproduct.quote().coverageAmount())
                .bountyValue(subproduct.quote().bountyValue())
                .appliedTax(subproduct.quote().appliedTax())
                .taxValue(subproduct.quote().taxValue())
                .build();
    }
}
