package com.amandalyckenius.portfolio_backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String>{
    Optional<Project> findBySlug(String slug);
}
