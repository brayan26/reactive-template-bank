package com.lulobank.insurance.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public sealed abstract class AbstractRestClient permits MsBusqoRestClient {
    protected final WebClient webClient;

    protected abstract HttpHeaders getHeaders();

    protected abstract <R> Mono<R> mapResponse(ClientResponse clientResponse, Class<R> responseType);

    protected AbstractRestClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    protected <T> Mono<T> get(final String uri, final Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry());
    }

    protected <T, R> Mono<R> post(final String uri, T requestBody, final Class<R> responseType) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry());
    }

    protected <T, R> Mono<R> put(final String uri, final T requestBody, final Class<R> responseType) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry());
    }

    protected <T, R> Mono<R> patch(final String uri, final T requestBody, final Class<R> responseType) {
        return webClient.patch()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry());
    }

    protected <R> Mono<R> delete(final String uri, final Class<R> responseType) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(getHeaders()))
                .exchangeToMono(response -> this.mapResponse(response, responseType))
                .retryWhen(RetryPolicy.defaultRetry());
    }

}
