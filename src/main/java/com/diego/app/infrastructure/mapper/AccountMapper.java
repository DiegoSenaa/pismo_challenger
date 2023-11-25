package com.diego.app.infrastructure.mapper;

import com.diego.app.domain.dto.AccountRequest;
import com.diego.app.domain.dto.AccountResponse;
import com.diego.app.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account requestToAccount(AccountRequest request);

    AccountResponse accountToResponse(Account account);
}
