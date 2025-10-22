package com.lulobank.insurance.clients;

import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.exceptions.GenericSemanticErrorException;
import com.lulobank.insurance.adapters.busqo.response.BusqoErrorResponse;
import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;
import com.lulobank.insurance.server.RequestConfig;
import com.lulobank.insurance.validation.ValidationMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class MsBusqoClient {
    private final RequestConfig requestConfig;
    private final WebClient webClient;

    public MsBusqoClient(RequestConfig requestConfig, WebClient.Builder webClientBuilder) {
        this.requestConfig = requestConfig;
        this.webClient = webClientBuilder.build();
    }

    public Mono<BusqoResponse> post(String uri, Object requestBody, Map<String, String> headers) {
        log.info("Request Body: {}", requestBody);
        return this.webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(requestBody)
                .exchangeToMono(response -> {
                    //success response
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(BusqoResponse.class);
                    }
                    // error response
                    return response.bodyToMono(BusqoErrorResponse.class)
                            .flatMap(error -> Mono.error(new GenericSemanticErrorException(
                                    String.format("<MsBusqoClient - execute> %s", error.message()), error))
                            );
                })
                .timeout(Duration.ofSeconds(this.requestConfig.getTimeout()))
                .retryWhen(Retry.backoff(this.requestConfig.getRetry(), Duration.ofSeconds(this.requestConfig.getDuration()))
                        .filter(throwable -> throwable instanceof TimeoutException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                                new GenericBadRequestException(
                                        String.format(ValidationMessages.GENERIC_API_TIMEOUT_ERROR_MESSAGE, retrySignal.failure().getLocalizedMessage()),
                                        Map.of("error", ValidationMessages.BUSQO_TIMEOUT_ERROR_MESSAGE))
                        )
                )
                .onErrorMap(ex -> new GenericBadRequestException(
                        String.format("[BUSQO] Error al consumir el API externo, %s", ex.getLocalizedMessage()),
                        Map.of("error", ValidationMessages.BUSQO_GENERIC_ERROR_MESSAGE))
                );
    }
}
