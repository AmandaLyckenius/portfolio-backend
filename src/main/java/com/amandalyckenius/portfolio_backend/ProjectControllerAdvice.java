package com.amandalyckenius.portfolio_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectControllerAdvice {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> projectNotFoundHandler(ProjectNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
