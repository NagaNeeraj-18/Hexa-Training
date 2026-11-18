-- Set 2: Bank Branch Accounts  |  MySQL 8.0.16+ (CHECK constraints are enforced)
DROP DATABASE IF EXISTS bank_branch;
CREATE DATABASE bank_branch;
USE bank_branch;

CREATE TABLE branch (
    branch_id   INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(60) NOT NULL UNIQUE,
    city        VARCHAR(40) NOT NULL
);

CREATE TABLE relationship_manager (
    rm_id     INT AUTO_INCREMENT PRIMARY KEY,
    rm_name   VARCHAR(80) NOT NULL,
    branch_id INT NOT NULL,
    CONSTRAINT fk_rm_branch FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);

CREATE TABLE customer (
    customer_id  INT AUTO_INCREMENT PRIMARY KEY,
    full_name    VARCHAR(80)  NOT NULL,
    address_line VARCHAR(150) NOT NULL,
    city         VARCHAR(40)  NOT NULL,
    pincode      CHAR(6)      NOT NULL
);

CREATE TABLE account (
    account_id     INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(12)   NOT NULL,
    account_type   VARCHAR(10)   NOT NULL,
    rm_id          INT           NOT NULL,   -- branch is reachable via rm_id (avoids transitive dependency)
    balance        DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    opened_date    DATE          NOT NULL,
    CONSTRAINT uq_account_number UNIQUE (account_number),
    CONSTRAINT chk_balance       CHECK (balance >= 0),
    CONSTRAINT chk_account_type  CHECK (account_type IN ('SAVINGS','CURRENT','FD','RD')),
    CONSTRAINT fk_account_rm     FOREIGN KEY (rm_id) REFERENCES relationship_manager(rm_id)
);

-- Composite key: supports joint accounts (many customers <-> many accounts)
CREATE TABLE account_holder (
    account_id  INT NOT NULL,
    customer_id INT NOT NULL,
    is_primary  BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (account_id, customer_id),
    CONSTRAINT fk_ah_account  FOREIGN KEY (account_id)  REFERENCES account(account_id),
    CONSTRAINT fk_ah_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
