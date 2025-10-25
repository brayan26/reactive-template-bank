package com.lulobank.insurance.clients;

import com.lulobank.insurance.commons.log.HeaderSanitizer;
import com.lulobank.insurance.commons.log.LogSanitizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public abstract sealed class AbstractRestClient permits MsBusqoRestClient {
    @Value("${ http-client.retry }")
    private int maxAttempts;
    @Value("${ http-client.duration }")
    private int duration;
    private final WebClient webClient;

    protected abstract HttpHeaders getHeaders();

    protected abstract <R> Mono<R> mapResponse(ClientResponse clientResponse, Class<R> responseType);

    protected AbstractRestClient(final WebClient.Builder webClientBuilder, final String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    protected <T> Mono<T> get(final String uri, final Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        printLog(uri, headers, null);
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry(maxAttempts, duration));
    }

    protected <T, R> Mono<R> post(final String uri, final T requestBody, final Class<R> responseType) {
        HttpHeaders headers = getHeaders();
        printLog(uri, headers, requestBody);
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry(maxAttempts, duration));
    }

    protected <T, R> Mono<R> put(final String uri, final T requestBody, final Class<R> responseType) {
        HttpHeaders headers = getHeaders();
        printLog(uri, headers, requestBody);
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry(maxAttempts, duration));
    }

    protected <T, R> Mono<R> patch(final String uri, final T requestBody, final Class<R> responseType) {
        HttpHeaders headers = getHeaders();
        printLog(uri, headers, requestBody);
        return webClient.patch()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry(maxAttempts, duration));
    }

    protected <R> Mono<R> delete(final String uri, final Class<R> responseType) {
        HttpHeaders headers = getHeaders();
        printLog(uri, headers, null);
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry(maxAttempts, duration));
    }

    private <T> void printLog(final String url, final HttpHeaders headers, final T requestBody) {
        log.info("Request config:");
        log.info("____________________________");
        log.info("Request uri: {}", url);
        log.info("Request headers: {}", HeaderSanitizer.safeLog(headers));
        log.info("Request body: {}", LogSanitizer.safeLog(requestBody));
        log.info("____________________________");
    }
}
