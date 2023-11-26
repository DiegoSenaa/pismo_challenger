package com.diego.app.service.impl;

import com.diego.app.domain.dto.exception.DataIntegrityViolationException;
import com.diego.app.domain.dto.exception.ObjectNotFoundException;
import com.diego.app.domain.entity.Account;
import com.diego.app.repository.AccountRepository;
import com.diego.app.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account request) {
        findByDocument(request);
        log.info("createAccount document: {}", request.getDocumentNumber());
        return accountRepository.save(request);
    }
    private void findByDocument(Account dto) {
        log.info("searching document: {}", dto.getDocumentNumber());
        Optional<Account> account = accountRepository.findByDocumentNumber(dto.getDocumentNumber());
        if (account.isPresent() && account.get().getDocumentNumber().equals(dto.getDocumentNumber())) {
            throw new DataIntegrityViolationException("Documento já existe no sistema!");
        }
    }

    @Override
    public Account findById(int accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty()) {
            throw new ObjectNotFoundException("Documento não encontrado");
        }
        return account.get();
    }
}
