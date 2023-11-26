package com.diego.app.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_operation_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationType {

    @Id
    @Column(name = "operation_type_id")
    private Integer operationTypeId;

    @Column(name = "operation", unique = true, nullable = false)
    private String operation;
}