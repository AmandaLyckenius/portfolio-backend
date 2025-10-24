package com.amandalyckenius.portfolio_backend.dto;

import java.util.List;

public record ProjectRequestDTO (
        String title,
        String description,
        String githubUrl,
        String liveUrl,
        List<String>tech
){
}
