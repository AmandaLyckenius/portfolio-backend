package com.amandalyckenius.portfolio_backend.dto;

import java.util.List;

public record ProjectResponseDTO(
        String title,
        String description,
        String slug,
        String githubUrl,
        String liveUrl,
        List<String> tech
) {
}
