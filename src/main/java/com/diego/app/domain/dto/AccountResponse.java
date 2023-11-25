package com.diego.app.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    @Schema(description = "Client id", example="1")
    private Integer accountId;

    @Schema(description = "Client document number", example="12345678900")
    private String documentNumber;
}
