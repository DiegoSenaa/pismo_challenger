package com.diego.app.infrastructure.mapper;

import com.diego.app.domain.dto.TransactionRequest;
import com.diego.app.domain.dto.TransactionResponse;
import com.diego.app.domain.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction requestToTransaction(TransactionRequest request);

    TransactionResponse transactionToResponse(Transaction transaction);
}
