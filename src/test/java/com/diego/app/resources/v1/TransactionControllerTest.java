package com.diego.app.resources.v1;

import com.diego.app.domain.dto.account.AccountRequest;
import com.diego.app.domain.dto.transaction.TransactionRequest;
import com.diego.app.domain.dto.transaction.TransactionResponse;
import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.OperationType;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.infrastructure.mapper.TransactionMapper;
import com.diego.app.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

class TransactionControllerTest {

    public static final String DOCUMENT_NUMBER = "012345678900";
    public static final int ID = 1;
    public static final double AMOUNT = 20.00;
    public static final int OPERATION_TYPE_ID = 3;
    public static final String SAQUE = "SAQUE";
    @InjectMocks
    TransactionController resource;

    @Mock
    TransactionMapper transactionMapper;

    @Mock
    TransactionService transactionService;

    TransactionRequest request;
    TransactionResponse response;
    Transaction transaction;
    Account account;

    OperationType operationType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

        startUpTransaction();
    }

    @Test
    void whenCreateAccountReturnCreated() {
        when(transactionMapper.requestToTransaction(request)).thenReturn(transaction);
        when(transactionMapper.transactionToResponse(transaction)).thenReturn(response);

        when(transactionService.createTransaction(this.transaction)).thenReturn(this.transaction);

        ResponseEntity<TransactionResponse> response = resource.createTransaction(this.request);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    private void startUpTransaction() {
        request = TransactionRequest.builder().accountId(ID).amount(AMOUNT).operationTypeId(OPERATION_TYPE_ID).build();
        response = TransactionResponse.builder().transactionId(ID).operationTypeId(OPERATION_TYPE_ID).amount(-20.00).build();
        account = Account.builder().accountId(ID).documentNumber(DOCUMENT_NUMBER).build();
        operationType = OperationType.builder().operationTypeId(OPERATION_TYPE_ID).operation(SAQUE).build();
        transaction = Transaction.builder().account(account).operationType(operationType).amount(AMOUNT).transactionId(1).build();
    }

}