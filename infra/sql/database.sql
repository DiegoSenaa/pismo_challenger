use account_db;

-- Criação da tabela tb_operation_type
CREATE TABLE tb_operation_type (
    operation_type_id INT PRIMARY KEY,
    operation VARCHAR(255) UNIQUE NOT NULL);

-- Criação da tabela accounts
CREATE TABLE tb_accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    document_number VARCHAR(255) UNIQUE NOT NULL
);

-- Criação da tabela transactions
CREATE TABLE tb_transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    operation_type_id INT,
    amount DOUBLE,
    event_date TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES tb_accounts(account_id),
    FOREIGN KEY (operation_type_id) REFERENCES tb_operation_type(operation_type_id),
    INDEX idx_account_id (account_id),
    INDEX idx_operation_type_id (operation_type_id),
    INDEX idx_account_operation (account_id, operation_type_id)
);