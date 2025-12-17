package com.amandalyckenius.portfolio_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactRequestDTO(
        @NotBlank
        @Size(min = 1, max = 100)
        String name,

        @Email
        @NotBlank
        @Size(max = 255)
        String email,

        @NotBlank
        @Size(min = 5, max = 1000)
        @Pattern(
                regexp = "^[^<>]*$",
                message = "Message contains invalid characters."
        )
        String message
) {
}
