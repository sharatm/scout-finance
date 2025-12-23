package com.ksht.troop2605.scoutfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class MailTestController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/email")
    public String sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sharatmandava@hotmail.com"); // put YOUR email here
        message.setSubject("Scout Finance – Test Email");
        message.setText("If you received this, email is working!");

        //mailSender.send(message);
        try {
            mailSender.send(message);
            log.info("Email sent to {}", message.getTo()[0]);
        } catch (Exception e) {
            log.error("Email failed", e);
        }

        return "Email sent!";
    }
}

