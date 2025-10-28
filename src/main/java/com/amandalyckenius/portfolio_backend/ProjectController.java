package com.amandalyckenius.portfolio_backend;

import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping
    public List<ProjectResponseDTO> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{slug}")
    public ProjectResponseDTO getProjectBySlug(@PathVariable String slug) {
        return projectService.findProjectBySlug(slug);
    }

    @PostMapping("/create")
    public ProjectResponseDTO createProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO){
        return projectService.createProject(projectRequestDTO);
    }

}
