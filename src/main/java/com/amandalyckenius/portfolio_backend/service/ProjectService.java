package com.amandalyckenius.portfolio_backend.service;

import com.amandalyckenius.portfolio_backend.entity.Project;
import com.amandalyckenius.portfolio_backend.repository.ProjectRepository;
import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import com.amandalyckenius.portfolio_backend.exceptions.ProjectNotFoundException;
import com.amandalyckenius.portfolio_backend.exceptions.SlugAlreadyExistsException;
import com.amandalyckenius.portfolio_backend.mapper.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.Normalizer;
import java.util.List;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

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
            throw new SlugAlreadyExistsException("Slug already exists: " + slug);
        }

        Project project = projectMapper.toDomain(projectRequestDTO, slug);
        projectRepository.save(project);

        log.info("Successfully created new project with slug {}", slug);
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
