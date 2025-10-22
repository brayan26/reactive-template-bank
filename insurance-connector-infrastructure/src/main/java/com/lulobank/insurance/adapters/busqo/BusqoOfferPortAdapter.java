package com.lulobank.insurance.adapters.busqo;

import com.lulobank.insurance.clients.MsBusqoClient;
import com.lulobank.insurance.mapper.LuloOfferMapperFromBusqo;
import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.ports.busqo.IBusqoOfferPort;
import com.lulobank.insurance.utils.HeadersUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BusqoOfferPortAdapter implements IBusqoOfferPort {
    private final MsBusqoClient client;
    //TODO: get uri from env variables

    @Override
    public Mono<SoatOfferLulo> getSoatOffer(String idClient, BusqoRequest request, String token) {
        Map<String, String> headers = new HashMap<>();

        headers.put(HeadersUtil.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
        headers.put(HeadersUtil.ACCEPT_KEY, MediaType.APPLICATION_JSON_VALUE);
        headers.put(HeadersUtil.AUTHORIZATION_KEY, String.join(" ", "Bearer", token));

        return this.client.post("", request, headers)
                .map(response -> LuloOfferMapperFromBusqo.map(response, idClient));
    }
}
