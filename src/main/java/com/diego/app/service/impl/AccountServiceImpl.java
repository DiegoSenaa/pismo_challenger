package com.diego.app.service.impl;

import com.diego.app.domain.entity.Account;
import com.diego.app.repository.AccountRepository;
import com.diego.app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account request) {
        return accountRepository.save(request);
    }

    @Override
    public Account findById(int accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        return account.orElse(null);
    }
}
