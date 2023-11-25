package com.diego.app.service;

import com.diego.app.domain.dto.AccountRequest;
import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;

import java.util.Optional;

public interface AccountService {
        public AccountResponse createAccount(AccountRequest account);

        public AccountResponse findById(int accountId);
}
