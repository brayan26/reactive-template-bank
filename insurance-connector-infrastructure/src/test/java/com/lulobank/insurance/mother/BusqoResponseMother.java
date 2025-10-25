package com.lulobank.insurance.mother;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lulobank.insurance.adapters.busqo.response.BusqoResponse;

public final class BusqoResponseMother {

    private BusqoResponseMother() {
    }

    public static BusqoResponse random() {
        return BusqoResponseMother.parseResponse();
    }

    private static BusqoResponse parseResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(getJsonResponseMock(), BusqoResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JsonBusqoResponse: " + e.getMessage());
        }
    }

    private static String getJsonResponseMock() {
        return """
                {
                    "QuotePublicId": "0E6D081BAB2F4CE4A7960241148265D3",
                    "BusinessName": "",
                    "Name": "NAME",
                    "LastName": "APELLIDO",
                    "Identification": "123456789",
                    "IdentificationType": "50002",
                    "IdentificationTypeName": "CC",
                    "MobilePhone": "3003903553",
                    "Email": "yeison.buitrago@busqo.com",
                    "Address": "Calle 1 # 2 - 3",
                    "CityId": "16911001",
                    "CityName": "BOGOTA, D.C., BOGOTA",
                    "Plate": "SXM096",
                    "VehicleTypeId": "",
                    "VehicleTypeName": "",
                    "Displacement": "1399",
                    "Model": "2011",
                    "ServiceTypeId": "1020002",
                    "ServiceTypeName": "Publico",
                    "Brand": "HYUNDAI",
                    "Line": "ACCENTGL",
                    "EngineNumber": "G4EEA677535",
                    "ChassisNumber": "KMHCM41AABU593413",
                    "VinNumber": "KMHCM41AABU593413",
                    "Passengers": "5",
                    "LoadTotal": "0.00",
                    "HomologateResult": [
                        {
                            "VehicleTypeId": "6010008",
                            "VehicleTypeName": "Autos de Negocios y Taxis",
                            "Rate": "712",
                            "BountyValue": "218700",
                            "FOSYGATax": "113700",
                            "RUNTRate": "2400",
                            "TotalValue": "334800",
                            "codEstadoFecFasecolda": false
                        },
                        {
                            "VehicleTypeId": "6010010",
                            "VehicleTypeName": "Servicio Publico Intermunicipal",
                            "Rate": "911",
                            "BountyValue": "414900",
                            "FOSYGATax": "215700",
                            "RUNTRate": "2400",
                            "TotalValue": "633000",
                            "codEstadoFecFasecolda": false
                        }
                    ],
                    "ActualSOATDueDate": "20260207",
                    "StartDate": "20260208",
                    "EndDate": "20270207",
                    "SOAT_AP": null,
                    "Value_AP": null,
                    "Coverage_AP": null,
                    "Rate": "",
                    "BountyValue": "0",
                    "FOSYGATax": "0",
                    "RUNTRate": "0",
                    "TotalValue": "0",
                    "codEstadoFecFasecolda": false,
                    "LawDiscountRate": "0",
                    "LawDiscountValue": "0",
                    "NonDiscountCausalCode": "",
                    "NonDiscountCausalDescription": "",
                    "ApAvailable": false,
                    "TotalValueWithoutDiscount": "0",
                    "TotalSummedValue": null,
                    "IncludeRc": false,
                    "RcPolicyEnabled": false,
                    "RcPolicyQuote": null,
                    "PersonalInformation": {
                        "BusinessName": "",
                        "Name": "NAME",
                        "LastName": "APELLIDO",
                        "Identification": "123456789",
                        "IdentificationType": "50002",
                        "IdentificationTypeName": "CC",
                        "MobilePhone": "3003903553",
                        "Email": "yeison.buitrago@busqo.com",
                        "Address": "Calle 1 # 2 - 3"
                    },
                    "Subproducts": [
                        {
                            "Type": "AP",
                            "Include": false,
                            "Available": true,
                            "PlanId": "1",
                            "HasIndependedTotalValue": false,
                            "Quote": {
                                "CoverageAmount": 7000000.00,
                                "BountyValue": null,
                                "TotalValue": 19900.00,
                                "AppliedTax": null,
                                "TaxValue": null
                            }
                        },
                        {
                            "Type": "AP",
                            "Include": false,
                            "Available": true,
                            "PlanId": "2",
                            "HasIndependedTotalValue": false,
                            "Quote": {
                                "CoverageAmount": 10000000.00,
                                "BountyValue": null,
                                "TotalValue": 24900.00,
                                "AppliedTax": null,
                                "TaxValue": null
                            }
                        },
                        {
                            "Type": "TERCERO",
                            "Include": false,
                            "Available": true,
                            "PlanId": "203",
                            "HasIndependedTotalValue": true,
                            "Quote": {
                                "CoverageAmount": 25000000.0,
                                "BountyValue": 74958.0,
                                "TotalValue": 89200.0,
                                "AppliedTax": null,
                                "TaxValue": 14242.0
                            }
                        },
                        {
                            "Type": "TERCERO",
                            "Include": false,
                            "Available": true,
                            "PlanId": "204",
                            "HasIndependedTotalValue": true,
                            "Quote": {
                                "CoverageAmount": 50000000.0,
                                "BountyValue": 93445.0,
                                "TotalValue": 111200.0,
                                "AppliedTax": null,
                                "TaxValue": 17755.0
                            }
                        }
                    ]
                }
                """;
    }
}
