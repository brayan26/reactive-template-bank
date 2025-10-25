package com.lulobank.insurance.entry_points.handlers;

import com.lulobank.insurance.commons.log.LogSanitizer;
import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;
import com.lulobank.insurance.mapper.BusqoMapper;
import com.lulobank.insurance.server.BusqoConfig;
import com.lulobank.insurance.services.BusqoOfferService;
import com.lulobank.insurance.validation.DtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SoatOffersHandler {
    private final BusqoOfferService service;
    private final DtoValidator validator;
    private final BusqoMapper mapper;
    private final BusqoConfig busqoConfig;

    public Mono<ServerResponse> getOffers(final ServerRequest request) {
        return request.bodyToMono(SoatOffersRequestDto.class)
                .doOnNext(dto -> log.info("INIT getOffers requestDto: {}", LogSanitizer.safeLog(dto)))
                .doOnNext(validator::validate)
                .flatMap(dto ->
                        this.service.getOffer(dto.idClient(), mapper.toDomain(dto, busqoConfig.parse())))
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(response));
    }
}
