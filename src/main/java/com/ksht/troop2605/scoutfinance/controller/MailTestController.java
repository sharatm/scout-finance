package com.ksht.troop2605.scoutfinance.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksht.troop2605.scoutfinance.entity.ExpenseClaim;
import com.ksht.troop2605.scoutfinance.repository.ExpenseClaimRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/test")
@Slf4j
@AllArgsConstructor
public class MailTestController {

    @Autowired
    private final ExpenseClaimRepository repo;

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

    @PostMapping("/api/expenses")
public ExpenseClaim submitExpense(@RequestBody ExpenseClaim claim) {
    claim.setStatus(ExpenseClaim.Status.SUBMITTED);
    claim.setSubmittedAt(LocalDateTime.now());
    repo.save(claim);

    // send email
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(claim.getEmail() != null ? claim.getEmail() : "default@domain.com");
    message.setSubject("Expense Submitted Successfully");
    message.setText("Hello " + claim.getFullName() + ",\n\n" +
                    "Your expense for event " + claim.getEventName() +
                    " has been submitted successfully.\n\n" +
                    "Amount: " + claim.getAmount() + "\n" +
                    "Description: " + claim.getDescription() + "\n" +
                    "Store Names: " + claim.getStoreNames() + "\n" +
                    "Shopping Date: " + claim.getShoppingDate() + "\n\n" +
                    "Thank you.");

    mailSender.send(message);

    return claim;
}
}

