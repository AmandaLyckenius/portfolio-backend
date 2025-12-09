package com.amandalyckenius.portfolio_backend.advice;

import com.amandalyckenius.portfolio_backend.exceptions.ProjectNotFoundException;
import com.amandalyckenius.portfolio_backend.exceptions.SlugAlreadyExistsException;
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

    @ExceptionHandler(SlugAlreadyExistsException.class)
    public ResponseEntity<String> slugAlreadyExistsHandler(SlugAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
