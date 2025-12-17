package com.amandalyckenius.portfolio_backend.controller;

import com.amandalyckenius.portfolio_backend.dto.ContactRequestDTO;
import com.amandalyckenius.portfolio_backend.service.ContactService;
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

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> sendContactMessage(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        contactService.handleContact(contactRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
