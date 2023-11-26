package com.diego.app.domain.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    @Schema(description = "Client id", example="1")
    private Integer accountId;

    @Schema(description = "Client document number", example="12345678900")
    private String documentNumber;
}
