package com.diego.app.resources.v1;

import com.diego.app.domain.dto.AccountRequest;
import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;
import com.diego.app.infrastructure.mapper.AccountMapper;
import com.diego.app.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1/accounts")
@Tag(name = "AccountController", description = "Operation to account management ")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get client by account id")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") int accountId) {
        AccountResponse account = accountService.findById(accountId);
        if (!Objects.isNull(account)) {
            return ResponseEntity.ok(account);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create  a account")
    public ResponseEntity<AccountResponse> createClient(@RequestBody AccountRequest accountRequest, UriComponentsBuilder uriBuilder) {
       AccountResponse response = accountService.createAccount(accountRequest);

        URI uri = uriBuilder.path("/v1/accounts/{id}").buildAndExpand(response.getAccountId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
