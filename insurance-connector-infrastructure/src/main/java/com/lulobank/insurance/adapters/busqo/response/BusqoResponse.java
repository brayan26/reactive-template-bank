package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record BusqoResponse(
        @JsonProperty("QuotePublicId")
        String quotePublicId,
        @JsonProperty("BusinessName")
        String businessName,
        @JsonProperty("Name")
        String name,
        @JsonProperty("LastName")
        String lastName,
        @JsonProperty("Identification")
        String identification,
        @JsonProperty("IdentificationType")
        String identificationType,
        @JsonProperty("IdentificationTypeName")
        String identificationTypeName,
        @JsonProperty("MobilePhone")
        String mobilePhone,
        @JsonProperty("Email")
        String email,
        @JsonProperty("Address")
        String address,
        @JsonProperty("CityId")
        String cityId,
        @JsonProperty("CityName")
        String cityName,
        @JsonProperty("Plate")
        String plate,
        @JsonProperty("VehicleTypeId")
        String vehicleTypeId,
        @JsonProperty("VehicleTypeName")
        String vehicleTypeName,
        @JsonProperty("Displacement")
        String displacement,
        @JsonProperty("Model")
        String model,
        @JsonProperty("ServiceTypeId")
        String serviceTypeId,
        @JsonProperty("ServiceTypeName")
        String serviceTypeName,
        @JsonProperty("Brand")
        String brand,
        @JsonProperty("Line")
        String line,
        @JsonProperty("EngineNumber")
        String engineNumber,
        @JsonProperty("ChassisNumber")
        String chassisNumber,
        @JsonProperty("VinNumber")
        String vinNumber,
        @JsonProperty("Passengers")
        String passengers,
        @JsonProperty("LoadTotal")
        String loadTotal,
        @JsonProperty("HomologateResult")
        List<HomologateResult> homologateResult,
        @JsonProperty("ActualSOATDueDate")
        String actualSOATDueDate,
        @JsonProperty("StartDate")
        String startDate,
        @JsonProperty("EndDate")
        String endDate,
        @JsonProperty("SOAT_AP")
        String soatAP,
        @JsonProperty("Value_AP")
        String value_AP,
        @JsonProperty("Coverage_AP")
        String coverage_AP,
        @JsonProperty("Rate")
        String rate,
        @JsonProperty("BountyValue")
        String bountyValue,
        @JsonProperty("FOSYGATax")
        String fosygaTax,
        @JsonProperty("RUNTRate")
        String runtRate,
        @JsonProperty("TotalValue")
        String totalValue,
        @JsonProperty("codEstadoFecFasecolda")
        String codEstadoFecFasecolda,
        @JsonProperty("LawDiscountRate")
        String lawDiscountRate,
        @JsonProperty("LawDiscountValue")
        String lawDiscountValue,
        @JsonProperty("NonDiscountCausalCode")
        String nonDiscountCausalCode,
        @JsonProperty("NonDiscountCausalDescription")
        String nonDiscountCausalDescription,
        @JsonProperty("ApAvailable")
        boolean apAvailable,
        @JsonProperty("TotalValueWithoutDiscount")
        String totalValueWithoutDiscount,
        @JsonProperty("TotalSummedValue")
        BigDecimal totalSummedValue,
        @JsonProperty("IncludeRc")
        boolean includeRc,
        @JsonProperty("RcPolicyEnabled")
        boolean rcPolicyEnabled,
        @JsonProperty("RcPolicyQuote")
        String rcPolicyQuote,
        @JsonProperty("PersonalInformation")
        PersonalInformation personalInformation,
        @JsonProperty("Subproducts")
        List<Subproducts> subproducts
) {
}
