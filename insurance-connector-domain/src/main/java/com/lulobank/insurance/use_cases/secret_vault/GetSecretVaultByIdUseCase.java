package com.lulobank.insurance.use_cases.secret_vault;

import com.lulobank.insurance.constants.ErrorMessages;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import com.lulobank.secretsprovider.sdk.client.SecretsProviderClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetSecretVaultByIdUseCase {
    private final SecretsProviderClient secretsProviderClient;

    public GetSecretVaultByIdUseCase(SecretsProviderClient secretsProviderClient) {
        this.secretsProviderClient = secretsProviderClient;
    }

    public Mono<String> execute(String secretId) {
        return Mono.fromCallable(() -> this.secretsProviderClient.getSecretsById(secretId).getSecretValue())
                .onErrorMap(ex -> new GenericBadRequestException(
                        String.format("<GetSecretVaultByIdUseCase - execute> Error leyendo secreto <%s> en Thycotic: %s ", secretId, ex.getMessage()),
                        BuildErrorResponseUtil.build(ErrorMessages.THYCOTIC_ERROR_CODE, ErrorMessages.THYCOTIC_ERROR_MESSAGE)));
    }
}
