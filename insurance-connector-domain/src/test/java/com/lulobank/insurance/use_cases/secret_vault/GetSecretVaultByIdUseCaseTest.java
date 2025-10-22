package com.lulobank.insurance.use_cases.secret_vault;

import com.lulobank.insurance.constants.ErrorMessages;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import com.lulobank.secretsprovider.sdk.client.SecretsProviderClient;
import com.lulobank.secretsprovider.sdk.model.GetSecretByIdResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

class GetSecretVaultByIdUseCaseTest {

    private SecretsProviderClient secretsProviderClient;
    private GetSecretVaultByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        secretsProviderClient = mock(SecretsProviderClient.class);
        useCase = new GetSecretVaultByIdUseCase(secretsProviderClient);
    }

    @Test
    void shouldReturnSecretSuccessfully() {
        // Arrange
        String secretId = String.valueOf((int) (Math.random() * (9000 - 1000 + 1) + 1000));
        String expectedSecret = UUID.randomUUID().toString();
        GetSecretByIdResponse response = new GetSecretByIdResponse(expectedSecret);

        when(secretsProviderClient.getSecretsById(secretId)).thenReturn(response);

        // Act & Assert
        StepVerifier.create(useCase.execute(secretId))
                .expectNext(expectedSecret)
                .verifyComplete();

        verify(secretsProviderClient).getSecretsById(secretId);
    }

    @Test
    void shouldReturnGenericBadRequestExceptionOnError() {
        String secretId = String.valueOf((int) (Math.random() * (9000 - 1000 + 1) + 1000));
        String errorMessage = String.format("Error leyendo secreto <%s>", secretId);

        RuntimeException exception = new RuntimeException("Thycotic service unavailable");

        when(secretsProviderClient.getSecretsById(secretId)).thenThrow(exception);

        // Act & Assert
        StepVerifier.create(useCase.execute(secretId))
                .expectErrorMatches(throwable ->
                        throwable instanceof GenericBadRequestException &&
                                throwable.getMessage().contains(errorMessage))
                .verify();

        verify(secretsProviderClient).getSecretsById(secretId);
    }
}
