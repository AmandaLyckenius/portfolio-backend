package com.amandalyckenius.portfolio_backend;

import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {
    private final List<ProjectResponseDTO> projects = List.of(
            new ProjectResponseDTO(
                    "Vision Capsule",
                    "Time-capsule web app that schedules messages for future delivery.",
                    "vision-capsule",
                    "https://github.com/AmandaLyckenius/vision-capsule",
                    "https://visioncapsule.app",
                    List.of("React", "TypeScript", "Spring Boot", "MongoDB")

            ),
            new ProjectResponseDTO(
                    "Lagom Kul",
                    "'lagom' fun app with randomised jokes",
                    "lagom-kul",
                    "https://github.com/BugBusters-G1/agprojekt",
                    "https://agprojekt.vercel.app",
                    List.of("React", "TypeScript", "Spring Boot", "MongoDB")
            ),
            new ProjectResponseDTO(
                    "ToDo app",
                    "ToDo app that helps you organize your tasks",
                    "ws-app",
                    "https://github.com/AmandaLyckenius/ws_app",
                    "",
                    List.of("React", "TypeScript", "Spring Boot", "MongoDB")

            ));

    public List<ProjectResponseDTO> findAll() {
        return projects;
    }

    public ProjectResponseDTO findProjectBySlug(String slug) {
        return projects.stream()
                .filter(p -> p.slug().equals(slug))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Project not found: " + slug
                ));
    }
}
