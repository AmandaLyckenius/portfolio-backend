package com.amandalyckenius.portfolio_backend.service;

import com.amandalyckenius.portfolio_backend.dto.ContactRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final JavaMailSender javaMailSender;
    private final String toAddress;
    private final String fromAddress;
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public ContactService(JavaMailSender javaMailSender, @Value("${portfolio.contact.to}") String toAddress, @Value("${portfolio.contact.from}") String fromAddress) {
        this.javaMailSender = javaMailSender;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
    }

    public void handleContact(ContactRequestDTO dto){
        String name = dto.name().trim();
        String email = dto.email().trim();
        String messageBody = dto.message().trim();

        String maskedEmail = maskEmail(email);

        log.info("Received contact request from {} <{}>", name, maskedEmail);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setFrom(fromAddress);
        message.setReplyTo(email);
        message.setSubject("New portfolio contact from " + name);

        message.setText("""
            Name: %s
            Email: %s

            Message:
            %s
            """.formatted(name, email, messageBody));

        javaMailSender.send(message);

        log.info("Successfully sent contact email for {}", maskedEmail);

    }

    private String maskEmail(String email) {
        return email.replaceAll("(?<=.).(?=[^@]*?@)", "*");
    }

}
