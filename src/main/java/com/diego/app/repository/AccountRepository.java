package com.diego.app.repository;

import com.diego.app.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByDocumentNumber(String documentNumber);
}