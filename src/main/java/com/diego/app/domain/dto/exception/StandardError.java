package com.diego.app.domain.dto.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StandardError {
    private LocalDate timestamp;
    private Integer status;
    private String error;
    private String path;
}
