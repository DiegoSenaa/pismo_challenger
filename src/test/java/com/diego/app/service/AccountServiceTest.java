package com.diego.app.service;

import com.diego.app.domain.entity.Account;
import com.diego.app.infrastructure.mapper.AccountMapper;
import com.diego.app.repository.AccountRepository;
import com.diego.app.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    public static final String DOCUMENT_NUMBER = "012345678900";
    public static final int ID = 1;

    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    private Account account;

    private Optional<Account> optionalAccount;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AccountServiceImpl(repository);
        startAccount();
    }

    @Test
    void whenCreateThenReturnAnSuccess() {
        when(repository.save(any())).thenReturn(account);

        Account response = service.createAccount(account);

        assertNotNull(response);
        assertEquals(Account.class, response.getClass());
        assertEquals(ID, response.getAccountId());
        assertEquals(DOCUMENT_NUMBER, response.getDocumentNumber());

    }

    @Test
    void whenFindByIdThenReturnAnAccountInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalAccount);

        Account response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Account.class, response.getClass());
        assertEquals(ID, response.getAccountId());
        assertEquals(DOCUMENT_NUMBER, response.getDocumentNumber());
    }

    private void startAccount() {
        account = Account.builder().accountId(ID).documentNumber(DOCUMENT_NUMBER).build();
        optionalAccount = Optional.of(account);
    }
}