package com.lulobank.insurance.adapters.busqo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonalInformation(
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
        String address
) {
}
