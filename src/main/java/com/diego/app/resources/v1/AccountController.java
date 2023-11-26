package com.diego.app.resources.v1;

import com.diego.app.domain.dto.AccountRequest;
import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;
import com.diego.app.infrastructure.mapper.AccountMapper;
import com.diego.app.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/v1/accounts")
@Tag(name = "AccountController", description = "Operation to account management ")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get client by account id")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") int accountId) {
        Account account = accountService.findById(accountId);
        if (!Objects.isNull(account)) {
            return ResponseEntity.ok(accountMapper.accountToResponse(account));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create  a account")
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    public ResponseEntity<AccountResponse> createClient(@RequestBody @Valid AccountRequest accountRequest) {
       Account account = accountService.createAccount(accountMapper.requestToAccount(accountRequest));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getAccountId()).toUri();
        return ResponseEntity.created(uri).body(accountMapper.accountToResponse(account));
    }

}
