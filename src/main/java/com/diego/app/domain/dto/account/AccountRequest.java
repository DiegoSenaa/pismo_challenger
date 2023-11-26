package com.diego.app.domain.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @Schema(description = "Client document number", example="12345678900")
    @NotBlank
    private String documentNumber;
}