package com.lulobank.insurance.use_cases.busqo;

import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.ports.busqo.IBusqoOfferPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public final class BusqoOfferGetterUseCase {
    private final IBusqoOfferPort busqoOfferPort;

    public BusqoOfferGetterUseCase(final IBusqoOfferPort busqoOfferPort) {
        this.busqoOfferPort = busqoOfferPort;
    }

    public Mono<SoatOfferLulo> execute(final String idClient, final BusqoRequest request) {
        return this.busqoOfferPort.getSoatOffer(idClient, request);
    }
}
