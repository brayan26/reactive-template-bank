package com.lulobank.insurance.entry_points.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PhoneRequest(
        @NotNull
        @NotBlank
        String number,
        @NotNull
        @NotBlank
        String prefix
) {
}
