package com.ksht.troop2605.scout_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksht.troop2605.scout_finance.entity.ExpenseClaim;

public interface ExpenseClaimRepository
        extends JpaRepository<ExpenseClaim, Long> {
}

