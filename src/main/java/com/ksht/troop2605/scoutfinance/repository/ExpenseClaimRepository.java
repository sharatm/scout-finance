package com.ksht.troop2605.scoutfinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksht.troop2605.scoutfinance.entity.ExpenseClaim;

public interface ExpenseClaimRepository
        extends JpaRepository<ExpenseClaim, Long> {
}

