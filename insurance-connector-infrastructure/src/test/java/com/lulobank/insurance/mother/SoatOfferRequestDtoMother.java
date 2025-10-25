package com.lulobank.insurance.mother;

import com.lulobank.insurance.entry_points.dto.DocumentRequest;
import com.lulobank.insurance.entry_points.dto.PhoneRequest;
import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;

public final class SoatOfferRequestDtoMother {
    private SoatOfferRequestDtoMother() {
    }

    public static SoatOffersRequestDto random() {
        return new SoatOffersRequestDto(
                "uuid",
                "MHW850",
                new DocumentRequest("36539334", "CC"),
                new PhoneRequest("3034026610", "57"),
                "example@gmail.com",
                "Calle 1B 2 3"

        );
    }
}
