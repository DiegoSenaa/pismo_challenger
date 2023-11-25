package com.diego.app.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_operation_type")
@Data
public class OperationType {

    @Id
    @Column(name = "operation_type_id")
    private Integer operationTypeId;

    @Column(name = "operation", unique = true, nullable = false)
    private String operation;
}