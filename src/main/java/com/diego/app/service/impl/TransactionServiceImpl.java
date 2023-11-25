package com.diego.app.service.impl;

import com.diego.app.domain.dto.TransactionRequest;
import com.diego.app.domain.dto.TransactionResponse;
import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.OperationType;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.domain.strategy.transaction.TransactionOperationStrategy;
import com.diego.app.infrastructure.mapper.TransactionMapper;
import com.diego.app.repository.AccountRepository;
import com.diego.app.repository.OperationTypeRepository;
import com.diego.app.repository.TransactionRepository;
import com.diego.app.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Map<String, TransactionOperationStrategy> strategyMap;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final AccountRepository accountRepository;

    private final OperationTypeRepository operationTypeRepository;


    public TransactionServiceImpl(Map<String, TransactionOperationStrategy> strategyMap, TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountRepository accountRepository, OperationTypeRepository operationTypeRepository) {
        this.strategyMap = strategyMap;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new EntityNotFoundException("Account not found"));

        OperationType operationType = operationTypeRepository.findById(request.getAccountId()).orElseThrow(() -> new EntityNotFoundException("Operation not found"));

        String operation = operationType.getOperation().replaceAll("\\s+", "").toLowerCase();

        Transaction transaction = transactionMapper.requestToTransaction(request);
        Double convertedAmount = this.strategyMap.get(operation).calcValue(request.getAmount());
        transaction.setAmount(convertedAmount);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);

        Transaction transactionSaved = transactionRepository.save(transaction);
        return transactionMapper.transactionToResponse(transactionSaved);
    }
}
