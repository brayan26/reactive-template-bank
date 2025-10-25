package com.lulobank.insurance.entry_points.router;

import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;
import com.lulobank.insurance.entry_points.handlers.SoatOffersHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SoatOffersRouterFunction {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/soat-offers",
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST,
                    beanClass = SoatOffersHandler.class,
                    beanMethod = "getOffers",
                    operation = @Operation(
                            operationId = "getOffers",
                            summary = "Buscar ofertas de seguro SOAT",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = SoatOffersRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Oferta satisfactoria",
                                            content = @Content(mediaType = "application/json")
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Errores de validacion ",
                                            content = @Content(mediaType = "application/json")
                                    ),
                                    @ApiResponse(
                                            responseCode = "422",
                                            description = "Conflictos en los parametros de request Busqo",
                                            content = @Content(mediaType = "application/json")
                                    ),
                                    // add responses 400, 404, 422, 500
                            }

                    )
            )
    })
    public RouterFunction<ServerResponse> offersRouterFunction(final SoatOffersHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/soat-offers", handler::getOffers)
                .build();
    }
}
