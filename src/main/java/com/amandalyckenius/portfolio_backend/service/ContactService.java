package com.amandalyckenius.portfolio_backend.service;

import com.amandalyckenius.portfolio_backend.dto.ContactRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final JavaMailSender javaMailSender;
    private final String toAddress;
    private final String fromAddress;

    public ContactService(JavaMailSender javaMailSender, @Value("${portfolio.contact.to}") String toAddress, @Value("${portfolio.contact.from}") String fromAddress) {
        this.javaMailSender = javaMailSender;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
    }

    public void handleContact(ContactRequestDTO dto){
        String name = dto.name().trim();
        String email = dto.email().trim();
        String messageBody = dto.message().trim();

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


    }

}
