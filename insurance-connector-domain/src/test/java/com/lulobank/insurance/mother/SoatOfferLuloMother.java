package com.lulobank.insurance.mother;

import com.lulobank.insurance.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SoatOfferLuloMother {

    public static SoatOfferLulo random() {
        return SoatOfferLulo.builder()
                .idClient(UUID.randomUUID().toString())
                .providerQuoteId(UUID.randomUUID().toString())
                .clientInformation(mapClient())
                .vehicleInformation(mapVehicle())
                .build();
    }

    private static ClientInformation mapClient() {
        return new ClientInformation(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new Document(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                UUID.randomUUID().toString(),
                new Phone(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                UUID.randomUUID().toString()
        );
    }

    private static VehicleInformation mapVehicle() {
        return new VehicleInformation(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                List.of(new VehicleType(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        new CostDetails(new BigDecimal("100000.00"), "COP")
                )),
                List.of(new AdditionalProduct(
                        UUID.randomUUID().toString(),
                        Boolean.FALSE,
                        Boolean.TRUE,
                        UUID.randomUUID().toString(),
                        Boolean.FALSE,
                        new Quote(
                                new CostDetails(new BigDecimal("100000.00"), "COP"),
                                new BigDecimal("100000.00"),
                                new BigDecimal("100000.00"),
                                new BigDecimal("100000.00"),
                                new BigDecimal("100000.00")
                        )
                ))
        );
    }
}
