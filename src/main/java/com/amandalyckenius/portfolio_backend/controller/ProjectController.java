package com.amandalyckenius.portfolio_backend.controller;

import com.amandalyckenius.portfolio_backend.service.ProjectService;
import com.amandalyckenius.portfolio_backend.dto.ProjectRequestDTO;
import com.amandalyckenius.portfolio_backend.dto.ProjectResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProjectResponseDTO> getProjectBySlug(@PathVariable String slug) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findProjectBySlug(slug));
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectRequestDTO));
    }

}
