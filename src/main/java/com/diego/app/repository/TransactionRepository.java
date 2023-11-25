package com.diego.app.repository;

import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}