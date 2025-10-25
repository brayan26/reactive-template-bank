package com.lulobank.insurance.clients;

import com.lulobank.insurance.adapters.busqo.response.BusqoErrorResponse;
import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.exceptions.GenericSemanticErrorException;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.server.BusqoConfig;
import com.lulobank.insurance.commons.util.BuildErrorResponseUtil;
import com.lulobank.insurance.utils.HeadersUtil;
import com.lulobank.insurance.validation.ValidationMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public final class MsBusqoRestClient extends AbstractRestClient {
    private final BusqoConfig busqoConfig;
    @Value("${bq_bearer_token}")
    private String token;

    public MsBusqoRestClient(final WebClient.Builder builder, final BusqoConfig busqoConfig) {
        super(builder, busqoConfig.parse().baseUrl());
        this.busqoConfig = busqoConfig;
    }

    public Mono<BusqoResponse> post(final BusqoRequest requestBody) {
        return this.post(this.busqoConfig.getOfferUrl(), requestBody, BusqoResponse.class);
    }

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeadersUtil.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HeadersUtil.AUTHORIZATION_KEY,
                String.join(" ", "Bearer", token));
        return headers;
    }

    @Override
    protected <R> Mono<R> mapResponse(final ClientResponse response, final Class<R> responseType) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(responseType)
                    .doOnNext(busqoResponse -> log.info("Busqo Response: {}", busqoResponse));
        }

        if (response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(error -> Mono.error(new GenericBadRequestException(
                            "Error 500 in Busqo consumption: " + error,
                            BuildErrorResponseUtil.build(ValidationMessages.BUSQO_INTERNAL_SERVER_ERROR_CODE,
                                    ValidationMessages.BUSQO_INTERNAL_SERVER_ERROR_MESSAGE)
                    )));
        }

        return response.bodyToMono(BusqoErrorResponse.class)
                .flatMap(error -> {
                    String[] array = error.message().split(" - ");
                    String code = String.join("INS_", array[0]);
                    String message = array[1];

                    return Mono.error(new GenericSemanticErrorException(
                            String.format("<MsBusqoRestClient - mapResponse> %s", error.message()),
                            BuildErrorResponseUtil.build(code, message))
                    );
                });
    }
}

