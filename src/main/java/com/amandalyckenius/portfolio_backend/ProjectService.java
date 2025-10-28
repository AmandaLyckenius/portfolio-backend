package com.amandalyckenius.portfolio_backend;

import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAll().stream().map(
                project -> new ProjectResponseDTO(
                        project.getTitle(),
                        project.getDescription(),
                        project.getSlug(),
                        project.getGithubUrl(),
                        project.getLiveUrl(),
                        project.getTech()
                )
        ).toList();
    }

    public ProjectResponseDTO findProjectBySlug(String slug) {

        Project project = projectRepository.findBySlug(slug)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found: " + slug));

        return new ProjectResponseDTO(
                project.getTitle(),
                project.getDescription(),
                project.getSlug(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getTech()
        );
    }
}
