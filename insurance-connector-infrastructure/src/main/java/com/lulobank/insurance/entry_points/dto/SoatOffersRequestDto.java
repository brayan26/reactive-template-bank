package com.lulobank.insurance.entry_points.dto;

import com.lulobank.insurance.validation.ValidationMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SoatOffersRequestDto(
        @NotNull
        @NotBlank
        String idClient,
        @NotNull
        @Pattern(
                regexp = "^(?:[A-Z]{3}\\d{3}|[A-Z]{2}\\d{3}[A-Z]|[A-Z]{2}\\d{3}|\\d{3}[A-Z]{3})$",
                message = ValidationMessages.PLATE_VALIDATION)
        String plate,
        @Valid
        DocumentRequest document,
        @Valid
        PhoneRequest phone,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String address
) {
}
