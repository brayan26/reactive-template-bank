package com.lulobank.insurance.ports.busqo;

import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import reactor.core.publisher.Mono;

public interface IBusqoOfferPort {
    Mono<SoatOfferLulo> getSoatOffer(String idClient, BusqoRequest request, String token);
}
