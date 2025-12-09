package com.amandalyckenius.portfolio_backend.repository;

import com.amandalyckenius.portfolio_backend.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String>{
    Optional<Project> findBySlug(String slug);
}
