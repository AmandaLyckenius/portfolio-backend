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
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAll().stream().map(
                projectMapper::toResponse
        ).toList();
    }

    public ProjectResponseDTO findProjectBySlug(String slug) {
        Project project = projectRepository.findBySlug(slug)
                .orElseThrow( () -> new ProjectNotFoundException("Project not found: " + slug));

        return projectMapper.toResponse(project);
    }

    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO){

        String slug = generateSlug(projectRequestDTO.title());

        if(projectRepository.findBySlug(slug).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Slug already exists" + slug);
        }

        Project project = projectMapper.toDomain(projectRequestDTO, slug);
        projectRepository.save(project);

        return projectMapper.toResponse(project);
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
