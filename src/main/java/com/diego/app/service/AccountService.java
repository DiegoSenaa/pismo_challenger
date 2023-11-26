package com.diego.app.service;

import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;

public interface AccountService {
        public Account createAccount(Account account);

        public Account findById(int accountId);
}
