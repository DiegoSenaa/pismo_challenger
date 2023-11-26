package com.diego.app.service.impl;

import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.OperationType;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.domain.strategy.transaction.TransactionOperationStrategy;
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
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    public TransactionServiceImpl(Map<String, TransactionOperationStrategy> strategyMap, TransactionRepository transactionRepository, AccountRepository accountRepository, OperationTypeRepository operationTypeRepository) {
        this.strategyMap = strategyMap;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public Transaction createTransaction(Transaction request) {
        OperationType operationType = getOperationType(request);

        Account account  = getAccount(request);

        Transaction updatedTransaction = updateTransactionFields(request, account, operationType);

        return transactionRepository.save(updatedTransaction);
    }

    private Transaction updateTransactionFields(Transaction transaction, Account account, OperationType operationType) {
        String operation = operationType.getOperation().replaceAll("\\s+", "").toLowerCase();
        Double convertedAmount = strategyMap.get(operation).calcValue(transaction.getAmount());

        transaction.setAmount(convertedAmount);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);

        return transaction;
    }

    private Account getAccount(Transaction request) {
        return accountRepository.findById(request.getAccount().getAccountId()).orElseThrow(() -> new EntityNotFoundException("Account not found for ID: " + request.getAccount().getAccountId()));
    }

    private OperationType getOperationType(Transaction request) {
        return operationTypeRepository.findById(request.getOperationType().getOperationTypeId()).orElseThrow(() -> new EntityNotFoundException("OperationType not found for ID: " + request.getOperationType().getOperationTypeId()));
    }

}
