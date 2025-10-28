package com.amandalyckenius.portfolio_backend;

import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
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
    public ProjectResponseDTO createProject(@RequestBody ProjectRequestDTO projectRequestDTO){
        return projectService.createProject(projectRequestDTO);
    }

}
