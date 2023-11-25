package com.diego.app.service;

import com.diego.app.domain.dto.TransactionRequest;
import com.diego.app.domain.dto.TransactionResponse;

public interface TransactionService {

    public TransactionResponse createTransaction(TransactionRequest request);
}
