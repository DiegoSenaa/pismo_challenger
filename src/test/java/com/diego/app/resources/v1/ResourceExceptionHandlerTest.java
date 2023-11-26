package com.diego.app.resources.v1;

import com.diego.app.domain.dto.exception.DataIntegrityViolationException;
import com.diego.app.domain.dto.exception.ObjectNotFoundException;
import com.diego.app.domain.dto.exception.StandardError;
import com.diego.app.resources.ResourceExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceExceptionHandlerTest {

    private static final String OBJECT_MESSAGE = "Objeto não encontrado";
    private static final String DATA_INTEGRITY_MESSAGE = "Account já existe no sistema!";

    @InjectMocks
    ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDataIntegrityViolationThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .dataIntegrityViolation(new DataIntegrityViolationException(DATA_INTEGRITY_MESSAGE), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(DATA_INTEGRITY_MESSAGE, response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }

    @Test
    void whenObjectNotFoundThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .objectNotFound(new ObjectNotFoundException(OBJECT_MESSAGE), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECT_MESSAGE, response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
    }
}
