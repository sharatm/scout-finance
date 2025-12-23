package com.ksht.troop2605.scoutfinance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "expense_claims")
@Data
public class ExpenseClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "shopping_date")
    private LocalDate shoppingDate;

    @Column(name = "store_names", length = 500)
    private String storeNames;

    @Column(length = 1000)
    private String description;

    private BigDecimal amount;

    @Column(name = "zelle_name")
    private String zelleName;

    @Column(name = "zelle_contact")
    private String zelleContact;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    public enum Status {
        SUBMITTED, APPROVED, REJECTED, PAID
    }
}

