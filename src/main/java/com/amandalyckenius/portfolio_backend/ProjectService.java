package com.amandalyckenius.portfolio_backend;

import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.text.Normalizer;
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

    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO){

        String slug = generateSlug(projectRequestDTO.title());

        Project project = new Project(
                projectRequestDTO.title(),
                projectRequestDTO.description(),
                slug,
                projectRequestDTO.githubUrl(),
                projectRequestDTO.liveUrl(),
                projectRequestDTO.tech()
        );

        projectRepository.save(project);

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(
                project.getTitle(),
                project.getDescription(),
                project.getSlug(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getTech()
        );

        return projectResponseDTO;
    }

    private String generateSlug(String title){
        if (title == null) return null;

        String slug = Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");

        return slug;
    }
}
