package com.diego.app.domain.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    @Schema(description = "Operation type id", example="1")
    private Integer operationTypeId;

    @Schema(description = "Total value of operation", example="20.00")
    private Double amount;

    @Schema(description = "Id of transaction", example="1")
    private Integer transactionId;
}