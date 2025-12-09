package com.amandalyckenius.portfolio_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Project {
    @Id
    private String id;
    private String title;
    private String description;
    private String slug;
    private String githubUrl;
    private String liveUrl;
    private List<String> tech;
    private String imageUrl;

    public Project(String title, String description, String slug, String githubUrl, String liveUrl, List<String> tech, String imageUrl) {
        this.title = title;
        this.description = description;
        this.slug = slug;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
        this.tech = tech;
        this.imageUrl = imageUrl;
    }

    public Project () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public List<String> getTech() {
        return tech;
    }

    public void setTech(List<String> tech) {
        this.tech = tech;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
