package com.amandalyckenius.portfolio_backend.service;

import com.amandalyckenius.portfolio_backend.dto.ContactRequestDTO;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final Resend resend;
    private final String toAddress;
    private final String fromAddress;
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public ContactService(@Value("${resend.api.key}") String resendApiKey, @Value("${portfolio.contact.to}") String toAddress, @Value("${portfolio.contact.from}") String fromAddress) {
        this.resend = new Resend(resendApiKey);
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
    }

    public void handleContact(ContactRequestDTO dto){
        String name = dto.name().trim();
        String email = dto.email().trim();
        String messageBody = dto.message().trim();

        String maskedEmail = maskEmail(email);

        log.info("Received contact request from {} <{}>", name, maskedEmail);

        String text = ("""
            Name: %s
            Email: %s

            Message:
            %s
            """.formatted(name, email, messageBody));

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from(fromAddress)
                .to(toAddress)
                .subject("New portfolio contact from " + name)
                .replyTo(email)
                .text(text)
                .build();

        try {
            CreateEmailResponse resp = resend.emails().send(options);
            log.info("Resend queued email id={} for {}", resp.getId(), maskedEmail);
        } catch (ResendException e) {
            log.error("Failed to send contact email for {}", maskedEmail, e);
            throw new RuntimeException("Failed to send contact email");
        }

    }

    private String maskEmail(String email) {
        return email.replaceAll("(?<=.).(?=[^@]*?@)", "*");
    }

}
