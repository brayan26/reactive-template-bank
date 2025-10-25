package com.lulobank.insurance.entry_points.dto;

import com.lulobank.insurance.commons.annotations.Sensitive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DocumentRequest(
        @NotNull
        @NotBlank
        @Sensitive
        String id,
        @NotNull
        @NotBlank
        String type
) {
}
