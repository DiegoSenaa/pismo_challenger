package com.diego.app.domain.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @Schema(description = "Client id", example = "1")
    @NotNull
    private Integer accountId;

    @Schema(description = "Operation type id", example = "1")
    @NotNull
    private Integer operationTypeId;


    @Schema(description = "Total value of operation", example = "20.00")
    @Min(0L)
    private Double amount;
}