package com.lulobank.insurance.use_cases.busqo;

import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.mother.BusqoRequestMother;
import com.lulobank.insurance.mother.SoatOfferLuloMother;
import com.lulobank.insurance.ports.busqo.IBusqoOfferPort;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

class BusqoOfferGetterUseCaseTest {


    private IBusqoOfferPort busqoOfferPort;
    private BusqoOfferGetterUseCase useCase;

    @BeforeEach
    void setUp() {
        busqoOfferPort = mock(IBusqoOfferPort.class);
        useCase = new BusqoOfferGetterUseCase(busqoOfferPort);
    }

    @Test
    void shouldReturnSoatOfferLuloSuccessfully() {
        // Arrange
        String idClient = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        SoatOfferLulo expectedOffer = SoatOfferLuloMother.random();

        when(busqoOfferPort.getSoatOffer(idClient, request, token))
                .thenReturn(Mono.just(expectedOffer));

        // Act & Assert
        StepVerifier.create(useCase.execute(idClient, request, token))
                .expectNext(expectedOffer)
                .verifyComplete();

        verify(busqoOfferPort).getSoatOffer(idClient, request, token);
    }

    @Test
    void shouldReturnErrorWhenPortFails() {
        // Arrange
        final String errorMessage = "Service error mock";
        String idClient = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        GenericBadRequestException error = new GenericBadRequestException(
                errorMessage,
                BuildErrorResponseUtil.build(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        when(busqoOfferPort.getSoatOffer(idClient, request, token))
                .thenReturn(Mono.error(error));

        // Act & Assert
        StepVerifier.create(useCase.execute(idClient, request, token))
                .expectErrorMatches(throwable -> throwable instanceof GenericBadRequestException &&
                        throwable.getMessage().equals(errorMessage))
                .verify();

        verify(busqoOfferPort).getSoatOffer(idClient, request, token);
    }

}
