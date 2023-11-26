package com.diego.app.infrastructure.mapper;

import com.diego.app.domain.dto.transaction.TransactionRequest;
import com.diego.app.domain.dto.transaction.TransactionResponse;
import com.diego.app.domain.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction requestToTransaction(TransactionRequest request);

    @Mapping(source = "operationType.operationTypeId", target = "operationTypeId")
    TransactionResponse transactionToResponse(Transaction transaction);
}
