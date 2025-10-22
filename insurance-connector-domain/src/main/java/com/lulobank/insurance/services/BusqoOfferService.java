package com.lulobank.insurance.services;

import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.use_cases.busqo.BusqoOfferGetterUseCase;
import com.lulobank.insurance.use_cases.secret_vault.GetSecretVaultByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BusqoOfferService {
    private final GetSecretVaultByIdUseCase getSecretVaultByIdUseCase;
    private final BusqoOfferGetterUseCase busqoOfferGetterUseCase;

    public Mono<SoatOfferLulo> getOffer(String idClient, BusqoRequest request) {
        //TODO: get secret id from ParameterStore or environment variables

        return this.getSecretVaultByIdUseCase.execute("2274")
                .flatMap(token -> this.busqoOfferGetterUseCase.execute(idClient, request, token));
    }
}
