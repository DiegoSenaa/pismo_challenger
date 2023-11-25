package com.diego.app.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "document_number", unique = true, nullable = false)
    private String documentNumber;
}
