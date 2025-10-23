package com.lulobank.insurance.clients;

import com.lulobank.insurance.adapters.busqo.response.BusqoErrorResponse;
import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;
import com.lulobank.insurance.exceptions.GenericSemanticErrorException;
import com.lulobank.insurance.model.busqo.BusqoRequest;
import com.lulobank.insurance.server.BusqoConfig;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import com.lulobank.insurance.utils.HeadersUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public final class MsBusqoRestClient extends AbstractRestClient {
    @Value("${bq_bearer_token-qa}")
    private String token;

    private final BusqoConfig busqoConfig;

    public MsBusqoRestClient(WebClient.Builder builder, BusqoConfig busqoConfig) {
        super(builder, busqoConfig.parse().baseUrl());
        this.busqoConfig = busqoConfig;
    }

    public Mono<BusqoResponse> post(BusqoRequest requestBody) {
        return this.post(this.busqoConfig.getOfferUrl(), requestBody, BusqoResponse.class);
    }

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeadersUtil.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HeadersUtil.ACCEPT_KEY, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HeadersUtil.AUTHORIZATION_KEY, String.join(" ", "Bearer", token));
        return headers;
    }

    @Override
    protected <R> Mono<R> mapResponse(ClientResponse response, Class<R> responseType) {
        //success response
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(responseType);
        }

        // error response
        return response.bodyToMono(BusqoErrorResponse.class)
                .flatMap(error -> {
                    String[] array = error.message().split("-");
                    String code = String.join("INS_", array[0]);
                    String message = array[1];

                    return Mono.error(new GenericSemanticErrorException(
                            String.format("<MsBusqoRestClient - mapResponse> %s", error.message()),
                            BuildErrorResponseUtil.build(code, message))
                    );
                });
    }
}

