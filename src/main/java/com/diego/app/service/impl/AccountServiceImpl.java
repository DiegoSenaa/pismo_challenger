package com.diego.app.service.impl;

import com.diego.app.domain.dto.AccountRequest;
import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;
import com.diego.app.infrastructure.mapper.AccountMapper;
import com.diego.app.repository.AccountRepository;
import com.diego.app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountResponse createAccount(AccountRequest request) {

        Account accountSaved =  accountRepository.save(accountMapper.requestToAccount(request));

        return accountMapper.accountToResponse(accountSaved);
    }

    @Override
    public AccountResponse findById(int accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        return account.map(accountMapper::accountToResponse).orElse(null);
    }
}
