package com.amandalyckenius.portfolio_backend.controller;

import com.amandalyckenius.portfolio_backend.dto.ContactRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {

    @PostMapping("/contact")
    public ResponseEntity<Void> sendContactMessage(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        String name = contactRequestDTO.name().trim();
        String email = contactRequestDTO.email().trim();
        String message = contactRequestDTO.message().trim();

        System.out.println("Contact message from " + name + " with email: " + email + " and message: " + message);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
