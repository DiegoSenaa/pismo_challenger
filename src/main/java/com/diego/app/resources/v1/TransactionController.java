package com.diego.app.resources.v1;

import com.diego.app.domain.dto.transaction.TransactionRequest;
import com.diego.app.domain.dto.transaction.TransactionResponse;
import com.diego.app.domain.entity.Transaction;
import com.diego.app.infrastructure.mapper.TransactionMapper;
import com.diego.app.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
@Slf4j
@Tag(name = "TransactionController", description = "Transaction operations ")
public class TransactionController {

    private final TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    @Operation(summary = "Create transaction")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest request) {

        Transaction transaction = transactionService.createTransaction(transactionMapper.requestToTransaction(request));
        TransactionResponse response = transactionMapper.transactionToResponse(transaction);

        log.info("response transaction: {}", response);
        return ResponseEntity.ok().body(response);
    }
}
