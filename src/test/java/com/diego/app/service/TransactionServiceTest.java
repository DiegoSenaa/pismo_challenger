package com.diego.app.service;

import com.diego.app.domain.entity.Account;
import com.diego.app.domain.entity.OperationType;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.domain.strategy.transaction.*;
import com.diego.app.repository.AccountRepository;
import com.diego.app.repository.OperationTypeRepository;
import com.diego.app.repository.TransactionRepository;
import com.diego.app.service.impl.TransactionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

    public static final String DOCUMENT_NUMBER = "012345678900";
    public static final int ID = 1;

    Transaction transaction;

    private Optional<Account> optionalAccount;

    public static final String SAQUE = "SAQUE";
    public static final String COMPRA_A_PRAZO = "COMPRA PARCELADA";
    public static final String COMPRA_A_VISTA = "COMPRA A VISTA";
    public static final String PAGAMENTO = "PAGAMENTO";
    public static final double AMOUNT = 20.00;

    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private Map<String, TransactionOperationStrategy> strategyMap;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationTypeRepository operationTypeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TransactionServiceImpl(strategyMap, transactionRepository, accountRepository, operationTypeRepository);
        startMocks();
    }

    @Test
    void testCreateTransactionSaque() {

        Optional<OperationType> operationType = generateOperationMock(SAQUE, 3);

        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationSaqueStrategy());
        when(accountRepository.findById(anyInt())).thenReturn(optionalAccount);
        when(operationTypeRepository.findById(anyInt())).thenReturn(operationType);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        Transaction response = service.createTransaction(transaction);

        assertNotNull(response);
        assertEquals(SAQUE, response.getOperationType().getOperation());
        assertEquals(3, response.getOperationType().getOperationTypeId());
        assertEquals(ID, response.getTransactionId());
        assertEquals(Timestamp.class, response.getEventDate().getClass());
    }

    @Test
    void testCreateTransactionPagamento() {

        Optional<OperationType> operationType = generateOperationMock(PAGAMENTO, 4);

        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationPagamentoStrategy());
        when(accountRepository.findById(anyInt())).thenReturn(optionalAccount);
        when(operationTypeRepository.findById(anyInt())).thenReturn(operationType);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        Transaction response = service.createTransaction(transaction);

        assertNotNull(response);
        assertEquals(PAGAMENTO, response.getOperationType().getOperation());
        assertEquals(4, response.getOperationType().getOperationTypeId());
        assertEquals(ID, response.getTransactionId());
        assertEquals(Timestamp.class, response.getEventDate().getClass());
    }

    @Test
    void testCreateTransactionCompraAPrazo() {

        Optional<OperationType> operationType = generateOperationMock(COMPRA_A_PRAZO, 2);


        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationCompraPrazoStrategy());
        when(accountRepository.findById(anyInt())).thenReturn(optionalAccount);
        when(operationTypeRepository.findById(anyInt())).thenReturn(operationType);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        Transaction response = service.createTransaction(transaction);

        assertNotNull(response);
        assertEquals(COMPRA_A_PRAZO, response.getOperationType().getOperation());
        assertEquals(2, response.getOperationType().getOperationTypeId());
        assertEquals(ID, response.getTransactionId());
        assertEquals(Timestamp.class, response.getEventDate().getClass());
    }

    @Test
    void testCreateTransactionCompraAVista() {

        Optional<OperationType> operationType = generateOperationMock(COMPRA_A_VISTA, 1);

        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationCompraAvistaStrategy());
        when(accountRepository.findById(anyInt())).thenReturn(optionalAccount);
        when(operationTypeRepository.findById(anyInt())).thenReturn(operationType);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        Transaction response = service.createTransaction(transaction);

        assertNotNull(response);
        assertEquals(COMPRA_A_VISTA, response.getOperationType().getOperation());
        assertEquals(1, response.getOperationType().getOperationTypeId());
        assertEquals(ID, response.getTransactionId());
        assertEquals(Timestamp.class, response.getEventDate().getClass());
    }

    @Test
    void testCreateThenReturnAnEntityNotFoundExceptionToOperation() {

        Optional<OperationType> operationType = generateOperationMock(COMPRA_A_VISTA, 1);

        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationCompraAvistaStrategy());
        when(accountRepository.findById(anyInt())).thenReturn(optionalAccount);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        try {
            service.createTransaction(transaction);
        } catch (Exception ex) {
            assertEquals(EntityNotFoundException.class, ex.getClass());
            assertEquals("OperationType not found for ID: " + ID, ex.getMessage());
        }
    }

    @Test
    void testCreateThenReturnAnEntityNotFoundExceptionToAccount() {

        Optional<OperationType> operationType = generateOperationMock(COMPRA_A_VISTA, 1);

        when(strategyMap.get(anyString())).thenReturn(new TransactionOperationCompraAvistaStrategy());
        when(operationTypeRepository.findById(anyInt())).thenReturn(operationType);
        when(transactionRepository.save(any())).thenReturn(transaction);

        transaction.setOperationType(operationType.get());

        try {
            service.createTransaction(transaction);
        } catch (Exception ex) {
            assertEquals(EntityNotFoundException.class, ex.getClass());
            assertEquals("Account not found for ID: " + ID, ex.getMessage());
        }
    }


    private void startMocks() {
        optionalAccount = Optional.of(Account.builder().accountId(ID).documentNumber(DOCUMENT_NUMBER).build());
        transaction = Transaction.builder().account(optionalAccount.get()).amount(AMOUNT).transactionId(1).eventDate(new Timestamp(System.currentTimeMillis())).build();
    }

    private Optional<OperationType> generateOperationMock(String operation, int id) {
        return Optional.of(OperationType.builder().operationTypeId(id).operation(operation).build());
    }
}
