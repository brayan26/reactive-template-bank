package com.lulobank.insurance.mother;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lulobank.insurance.model.SoatOfferLulo;

public final class SoatOfferLuloMother {
    private SoatOfferLuloMother() {
    }

    public static SoatOfferLulo random() {
        return SoatOfferLuloMother.parseResponse();
    }

    private static SoatOfferLulo parseResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(getJsonResponseMock(), SoatOfferLulo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JsonSoatOfferLulo: " + e.getMessage());
        }
    }

    private static String getJsonResponseMock() {
        return """
                {
                    "idClient": "uuid",
                    "providerQuoteId": "0E6D081BAB2F4CE4A7960241148265D3",
                    "clientInformation": {
                        "name": "YOJAIRA",
                        "lastName": "PONZON PAJARO",
                        "businessName": "",
                        "document": {
                            "id": "36539334",
                            "type": "50002"
                        },
                        "email": "lextermontes1@gmail.com",
                        "phoneInfo": {
                            "phoneNumber": "3024026718",
                            "phonePrefix": null
                        },
                        "address": "Calle 1 # 2 - 3"
                    },
                    "vehicleInformation": {
                        "plate": "MHW850",
                        "model": "2013",
                        "brand": "RENAULT",
                        "serviceTypeId": "1020001",
                        "serviceTypeName": "Particular",
                        "vehicleTypeId": "6010006",
                        "passengers": "5",
                        "actualSOATDueDate": "20251024",
                        "startDate": "20251025",
                        "endDate": "20261024",
                        "vehicleType": [
                            {
                                "id": "6010006",
                                "name": "Autos Familiares",
                                "costDetails": {
                                    "totalValue": 675000,
                                    "currency": "COP"
                                }
                            }
                        ],
                        "additionalProducts": [
                            {
                                "type": "AP",
                                "include": false,
                                "available": true,
                                "planId": "3",
                                "hasIndependedTotalValue": true,
                                "quote": {
                                    "costDetails": {
                                        "totalValue": 19900.00,
                                        "currency": "COP"
                                    },
                                    "coverageAmount": 7000000.00,
                                    "bountyValue": null,
                                    "appliedTax": null,
                                    "taxValue": null
                                }
                            },
                            {
                                "type": "AP",
                                "include": false,
                                "available": true,
                                "planId": "5",
                                "hasIndependedTotalValue": true,
                                "quote": {
                                    "costDetails": {
                                        "totalValue": 39900.00,
                                        "currency": "COP"
                                    },
                                    "coverageAmount": 10000000.00,
                                    "bountyValue": null,
                                    "appliedTax": null,
                                    "taxValue": null
                                }
                            },
                            {
                                "type": "TERCERO",
                                "include": false,
                                "available": true,
                                "planId": "203",
                                "hasIndependedTotalValue": true,
                                "quote": {
                                    "costDetails": {
                                        "totalValue": 89200.0,
                                        "currency": "COP"
                                    },
                                    "coverageAmount": 25000000.0,
                                    "bountyValue": 74958.0,
                                    "appliedTax": null,
                                    "taxValue": 14242.0
                                }
                            },
                            {
                                "type": "TERCERO",
                                "include": false,
                                "available": true,
                                "planId": "204",
                                "hasIndependedTotalValue": true,
                                "quote": {
                                    "costDetails": {
                                        "totalValue": 111200.0,
                                        "currency": "COP"
                                    },
                                    "coverageAmount": 50000000.0,
                                    "bountyValue": 93445.0,
                                    "appliedTax": null,
                                    "taxValue": 17755.0
                                }
                            }
                        ]
                    }
                }
                """;
    }
}
