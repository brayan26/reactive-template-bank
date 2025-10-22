package com.lulobank.insurance.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lulobank.insurance.exceptions.GenericBadRequestException;
import com.lulobank.insurance.exceptions.GenericNotFoundException;
import com.lulobank.insurance.exceptions.GenericSemanticErrorException;
import com.lulobank.insurance.util.BuildErrorResponseUtil;
import com.lulobank.insurance.validation.ValidationMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Error: {}", ex.getMessage());

        ErrorResponse errorResponse = mapExceptionToErrorResponse(ex);

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorResponse.status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory bufferFactory = response.bufferFactory();

        try {
            byte[] body = objectMapper.writeValueAsBytes(errorResponse.body);
            return response.writeWith(wrapResponse(bufferFactory, body));
        } catch (JsonProcessingException e) {
            log.error("Error serializing error response", e);
            byte[] fallbackBody = ValidationMessages.ERROR_PARSING_JSON.getBytes(StandardCharsets.UTF_8);
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return response.writeWith(wrapResponse(bufferFactory, fallbackBody));
        }
    }

    private ErrorResponse mapExceptionToErrorResponse(Throwable ex) {
        return switch (ex) {
            case GenericBadRequestException e -> new ErrorResponse(HttpStatus.BAD_REQUEST, e.getError());
            case GenericNotFoundException e -> new ErrorResponse(HttpStatus.NOT_FOUND, e.getError());
            case GenericSemanticErrorException e -> new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, e.getError());
            case null, default -> new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    BuildErrorResponseUtil.build(
                            ValidationMessages.UNKNOW_ERROR_CODE,
                            ValidationMessages.UNKNOW_ERROR_DESCRIPTION
                    )
            );
        };
    }

    private Mono<DataBuffer> wrapResponse(DataBufferFactory bufferFactory, byte[] bytes) {
        return Mono.just(bufferFactory.wrap(bytes));
    }

    private record ErrorResponse(HttpStatus status, Object body) {
    }
}

