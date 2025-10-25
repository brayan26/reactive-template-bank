package com.lulobank.insurance.services;

import com.lulobank.insurance.commons.util.BuildErrorResponseUtil;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.exceptions.GenericNotFoundException;
import com.lulobank.insurance.exceptions.GenericSemanticErrorException;
import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.mother.BusqoRequestMother;
import com.lulobank.insurance.mother.SoatOfferLuloMother;
import com.lulobank.insurance.ports.busqo.IBusqoOfferPort;
import com.lulobank.insurance.use_cases.busqo.BusqoOfferGetterUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusqoOfferServiceTest {
    @Mock
    private IBusqoOfferPort busqoOfferPort;
    private BusqoOfferService service;

    @BeforeEach
    void setup() {
        BusqoOfferGetterUseCase useCase = new BusqoOfferGetterUseCase(busqoOfferPort);
        service = new BusqoOfferService(useCase);
    }

    @Test
    void shouldReturnSoatOfferLuloSuccessfully() {
        String idClient = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        SoatOfferLulo expectedOffer = SoatOfferLuloMother.random();

        when(busqoOfferPort.getSoatOffer(idClient, request))
                .thenReturn(Mono.just(expectedOffer));

        // Act & Assert
        StepVerifier.create(service.getOffer(idClient, request))
                .expectNext(expectedOffer)
                .verifyComplete();

        verify(busqoOfferPort).getSoatOffer(idClient, request);
    }

    @Test
    void shouldReturnErrorBadRequestWhenPortFails() {
        // Arrange
        final String errorCode = "INS_100";
        final String errorMessage = "Error mock";

        String idClient = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        GenericBadRequestException error = new GenericBadRequestException(
                errorMessage,
                BuildErrorResponseUtil.build(errorCode, errorMessage)
        );

        when(busqoOfferPort.getSoatOffer(idClient, request))
                .thenReturn(Mono.error(error));

        // Act & Assert
        StepVerifier.create(service.getOffer(idClient, request))
                .expectErrorMatches(throwable -> throwable instanceof GenericBadRequestException &&
                        throwable.getMessage().equals(errorMessage))
                .verify();

        verify(busqoOfferPort).getSoatOffer(idClient, request);
    }

    @Test
    void shouldReturnErrorNotFoundWhenPortFails() {
        // Arrange
        final String errorMessageResponse = "Service error mock not found";
        final String errorCode = "INS_101";
        final String errorMessage = "Error mock not found";

        String idClient = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        GenericNotFoundException error = new GenericNotFoundException(
                errorMessage,
                BuildErrorResponseUtil.build(errorCode, errorMessage)
        );

        when(busqoOfferPort.getSoatOffer(idClient, request))
                .thenReturn(Mono.error(error));

        // Act & Assert
        StepVerifier.create(service.getOffer(idClient, request))
                .expectErrorMatches(throwable -> throwable instanceof GenericNotFoundException &&
                        throwable.getMessage().equals(errorMessage))
                .verify();

        verify(busqoOfferPort).getSoatOffer(idClient, request);
    }

    @Test
    void shouldReturnSemanticErrorWhenPortFails() {
        // Arrange
        final String errorMessageResponse = "Service error mock semantic";
        final String errorCode = "INS_102";
        final String errorMessage = "Error mock semantic";

        String idClient = UUID.randomUUID().toString();
        BusqoRequest request = BusqoRequestMother.random();
        GenericSemanticErrorException error = new GenericSemanticErrorException(
                errorMessage,
                BuildErrorResponseUtil.build(errorCode, errorMessage)
        );

        when(busqoOfferPort.getSoatOffer(idClient, request))
                .thenReturn(Mono.error(error));

        // Act & Assert
        StepVerifier.create(service.getOffer(idClient, request))
                .expectErrorMatches(throwable -> throwable instanceof GenericSemanticErrorException &&
                        throwable.getMessage().equals(errorMessage))
                .verify();

        verify(busqoOfferPort).getSoatOffer(idClient, request);
    }
}
