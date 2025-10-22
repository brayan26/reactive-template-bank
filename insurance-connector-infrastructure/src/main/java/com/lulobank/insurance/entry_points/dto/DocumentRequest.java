package com.lulobank.insurance.entry_points.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DocumentRequest(
        @NotNull
        @NotBlank
        String id,
        @NotNull
        @NotBlank
        String type
) {
}
