package com.diego.app.service;

import com.diego.app.domain.dto.TransactionResponse;
import com.diego.app.domain.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction request);
}
