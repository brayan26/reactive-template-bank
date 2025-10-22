package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HomologateResult(
        @JsonProperty("VehicleTypeId")
        String vehicleTypeId,
        @JsonProperty("VehicleTypeName")
        String vehicleTypeName,
        @JsonProperty("Rate")
        String rate,
        @JsonProperty("BountyValue")
        String bountyValue,
        @JsonProperty("FOSYGATax")
        String fosygaTax,
        @JsonProperty("RUNTRate")
        String runTrate,
        @JsonProperty("TotalValue")
        String totalValue,
        @JsonProperty("codEstadoFecFasecolda")
        boolean codEstadoFecFasecolda
) {
}
