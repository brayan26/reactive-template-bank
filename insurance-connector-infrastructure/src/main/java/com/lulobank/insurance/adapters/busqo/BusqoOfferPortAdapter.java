package com.lulobank.insurance.adapters.busqo;

import com.lulobank.insurance.clients.MsBusqoRestClient;
import com.lulobank.insurance.mapper.LuloOfferMapperFromBusqo;
import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.ports.busqo.IBusqoOfferPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BusqoOfferPortAdapter implements IBusqoOfferPort {
    private final MsBusqoRestClient client;

    @Override
    public Mono<SoatOfferLulo> getSoatOffer(final String idClient, final BusqoRequest request) {
        log.info("Starting Busqo remote call, idClient: {}", idClient);
        return this.client.post(request)
                .map(response -> LuloOfferMapperFromBusqo.map(response, idClient));
    }
}
