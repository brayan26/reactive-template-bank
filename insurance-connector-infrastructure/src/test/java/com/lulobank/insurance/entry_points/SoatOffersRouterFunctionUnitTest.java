package com.lulobank.insurance.entry_points;

import com.lulobank.insurance.clients.MsBusqoRestClient;
import com.lulobank.insurance.entry_points.dto.SoatOffersRequestDto;
import com.lulobank.insurance.model.SoatOfferLulo;
import com.lulobank.insurance.mother.SoatOfferLuloMother;
import com.lulobank.insurance.mother.SoatOfferRequestDtoMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class SoatOffersRouterFunctionUnitTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private MsBusqoRestClient msBusqoRestClient;

    @Test
    void testRouterFunctionDirectlyWithStatusCode200() {
        final String idClient = "uuid";
        final SoatOfferLulo expectedResponse = SoatOfferLuloMother.random();
        final SoatOffersRequestDto dto = SoatOfferRequestDtoMother.random();

        RouterFunction<ServerResponse> router = RouterFunctions.route()
                .POST("/insurance-connector/api/v1/soat-offers", request ->
                        ServerResponse.ok().bodyValue(expectedResponse))
                .build();

        WebTestClient.bindToRouterFunction(router)
                .build()
                .post().uri("/insurance-connector/api/v1/soat-offers")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idClient").isEqualTo(idClient)
                .jsonPath("$.providerQuoteId").isEqualTo("0E6D081BAB2F4CE4A7960241148265D3");
    }

}
