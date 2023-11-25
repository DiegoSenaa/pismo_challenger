package com.diego.app.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    @Schema(description = "Client id", example="1")
    @NotBlank
    private Integer accountId;

    @NotBlank
    @Schema(description = "Operation type id", example="1")
    private Integer operationTypeId;

    @NotBlank
    @Schema(description = "Total value of operation", example="20.00")
    private Double amount;
}
