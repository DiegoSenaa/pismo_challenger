package com.diego.app.service.impl;

import com.diego.app.domain.dto.exception.ObjectNotFoundException;
import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.OperationType;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.domain.strategy.transaction.TransactionOperationStrategy;
import com.diego.app.repository.AccountRepository;
import com.diego.app.repository.OperationTypeRepository;
import com.diego.app.repository.TransactionRepository;
import com.diego.app.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
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

        log.info("save transaction: {}",updatedTransaction);
        return transactionRepository.save(updatedTransaction);
    }

    private Transaction updateTransactionFields(Transaction transaction, Account account, OperationType operationType) {
        String operation = operationType.getOperation().replaceAll("\\s+", "").toLowerCase();
        log.info("call strategy: {}",operation);
        Double convertedAmount = strategyMap.get(operation).calcValue(transaction.getAmount());

        transaction.setAmount(convertedAmount);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);

        log.info("return transaction: {}",transaction);
        return transaction;
    }

    private Account getAccount(Transaction request) {
        log.info("search client: {}",request.getAccount().getAccountId());
        return accountRepository.findById(request.getAccount().getAccountId()).orElseThrow(() -> new ObjectNotFoundException("Account not found for ID: " + request.getAccount().getAccountId()));
    }

    private OperationType getOperationType(Transaction request) {
        log.info("search operation: {}",request.getOperationType().getOperation());
        return operationTypeRepository.findById(request.getOperationType().getOperationTypeId()).orElseThrow(() -> new ObjectNotFoundException("OperationType not found for ID: " + request.getOperationType().getOperationTypeId()));
    }

}
