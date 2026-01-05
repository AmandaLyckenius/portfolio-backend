package com.amandalyckenius.portfolio_backend.advice;

import com.amandalyckenius.portfolio_backend.exceptions.ContactEmailException;
import com.amandalyckenius.portfolio_backend.exceptions.ProjectNotFoundException;
import com.amandalyckenius.portfolio_backend.exceptions.SlugAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> projectNotFoundHandler(ProjectNotFoundException exception){
        log.warn("Project not found: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(SlugAlreadyExistsException.class)
    public ResponseEntity<String> slugAlreadyExistsHandler(SlugAlreadyExistsException exception){
        log.warn("Slug already exists: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ContactEmailException.class)
    public ResponseEntity<String> ContactEmailExceptionHandler(ContactEmailException exception) {
        log.error("Failed to send email", exception);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Could not send email right now, please try again later");

    }

}
