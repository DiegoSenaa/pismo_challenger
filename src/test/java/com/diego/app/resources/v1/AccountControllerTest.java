package com.diego.app.resources.v1;

import com.diego.app.domain.dto.account.AccountRequest;
import com.diego.app.domain.dto.account.AccountResponse;
import com.diego.app.domain.dto.exception.DataIntegrityViolationException;
import com.diego.app.domain.dto.exception.ObjectNotFoundException;
import com.diego.app.domain.entity.Account;
import com.diego.app.infrastructure.mapper.AccountMapper;
import com.diego.app.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AccountControllerTest {

    public static final String DOCUMENT_NUMBER = "012345678900";
    public static final int ID = 1;
    @InjectMocks
    AccountController resource;

    @Mock
    AccountMapper accountMapper;

    @Mock
    AccountService accountService;

    AccountRequest request;
    AccountResponse response;
    Account account;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);

        startUpAccount();
    }

    @Test
    void whenCreateAccountReturnCreated() {
        when(accountService.createAccount(this.account)).thenReturn(this.account);
        when(accountMapper.requestToAccount(any())).thenReturn(account);


        ResponseEntity<AccountResponse> response = resource.createClient(this.request);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void whenCreateAccountReturnDataIntegrityViolation() {
        when(accountService.createAccount(this.account)).thenThrow(new DataIntegrityViolationException("Documento já existe no sistema!"));
        when(accountMapper.requestToAccount(any())).thenReturn(account);

        try {
            resource.createClient(request);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Documento já existe no sistema!", ex.getMessage());
        }
    }


    @Test
    void whenFindByIdThenReturnSuccess() {
        when(accountService.findById(anyInt())).thenReturn(account);
        when(accountMapper.requestToAccount(any())).thenReturn(account);
        when(accountMapper.accountToResponse(any())).thenReturn(response);


        ResponseEntity<AccountResponse> response = resource.getAccount(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(AccountResponse.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().getAccountId());
        assertEquals(DOCUMENT_NUMBER, response.getBody().getDocumentNumber());
    }

    @Test
    void whenFindByIdThenReturnNotFound() {
        when(accountService.findById(anyInt())).thenThrow(new ObjectNotFoundException("Documento não encontrado"));
        when(accountMapper.requestToAccount(any())).thenReturn(account);

        try {
            resource.getAccount(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Documento não encontrado", ex.getMessage());
        }
    }


    private void startUpAccount() {
        request = AccountRequest.builder().documentNumber(DOCUMENT_NUMBER).build();
        response = AccountResponse.builder().accountId(ID).documentNumber(DOCUMENT_NUMBER).build();
        account = Account.builder().accountId(ID).documentNumber(DOCUMENT_NUMBER).build();
    }
}
