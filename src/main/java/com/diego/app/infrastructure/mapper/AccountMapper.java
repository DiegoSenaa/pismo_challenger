package com.diego.app.infrastructure.mapper;

import com.diego.app.domain.dto.account.AccountRequest;
import com.diego.app.domain.dto.account.AccountResponse;
import com.diego.app.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account requestToAccount(AccountRequest request);

    AccountResponse accountToResponse(Account account);
}
