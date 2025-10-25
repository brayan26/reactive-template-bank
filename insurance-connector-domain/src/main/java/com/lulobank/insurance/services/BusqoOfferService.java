package com.lulobank.insurance.services;

import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.use_cases.busqo.BusqoOfferGetterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public final class BusqoOfferService {
    private final BusqoOfferGetterUseCase busqoOfferGetterUseCase;

    public Mono<SoatOfferLulo> getOffer(final String idClient, final BusqoRequest request) {
        return this.busqoOfferGetterUseCase.execute(idClient, request);
    }
}
