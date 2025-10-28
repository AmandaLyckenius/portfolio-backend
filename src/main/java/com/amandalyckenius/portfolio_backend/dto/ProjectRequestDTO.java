package com.amandalyckenius.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record ProjectRequestDTO (
        @NotBlank
        @Size(min=3, max=80)
        String title,

        @NotBlank
        @Size(min = 10, max = 500)
        String description,

        @NotBlank
        @Size(max = 2048)
        @URL(protocol = "https")
        String githubUrl,

        @Size(max = 2048)
        @URL(protocol = "https")
        String liveUrl,

        List<
        @NotNull
        @Size(min = 1, max = 30)
        @Pattern(regexp="^[A-Za-z0-9+.#\\-/]+(?: [A-Za-z0-9+.#\\-/]+)*$", message="Invalid tech tag")
        String>tech
){
}
