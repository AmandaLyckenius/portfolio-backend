package com.amandalyckenius.portfolio_backend.mapper;

import com.amandalyckenius.portfolio_backend.entity.Project;
import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toDomain(ProjectRequestDTO projectRequestDTO, String slug) {
        return new Project(
                projectRequestDTO.title(),
                projectRequestDTO.description(),
                slug,
                projectRequestDTO.githubUrl(),
                projectRequestDTO.liveUrl(),
                projectRequestDTO.tech(),
                projectRequestDTO.imageUrl()
        );
    }

    public ProjectResponseDTO toResponse(Project project){
        return new ProjectResponseDTO(
                project.getTitle(),
                project.getDescription(),
                project.getSlug(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getTech(),
                project.getImageUrl()
        );
    }



}
