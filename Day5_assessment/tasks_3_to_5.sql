USE bank_branch;

-- ================= TASK 3: DROP vs TRUNCATE vs DELETE =================
CREATE TABLE test_acc (id INT AUTO_INCREMENT PRIMARY KEY, note VARCHAR(20));
INSERT INTO test_acc (note) VALUES ('a'),('b'),('c');      -- AUTO_INCREMENT next = 4

-- DELETE (DML): row by row, WHERE allowed, can be rolled back, AUTO_INCREMENT NOT reset
START TRANSACTION;
DELETE FROM test_acc;
SELECT COUNT(*) AS rows_after_delete FROM test_acc;        -- 0
ROLLBACK;
SELECT COUNT(*) AS rows_after_rollback FROM test_acc;      -- 3 (rollback worked)
DELETE FROM test_acc;
INSERT INTO test_acc (note) VALUES ('d');
SELECT id FROM test_acc;                                   -- 4 (counter continued)

-- TRUNCATE (DDL): removes all rows, implicit commit (cannot roll back), AUTO_INCREMENT reset to 1
START TRANSACTION;
TRUNCATE TABLE test_acc;
ROLLBACK;                                                  -- no effect
SELECT COUNT(*) AS rows_after_truncate FROM test_acc;      -- 0
INSERT INTO test_acc (note) VALUES ('e');
SELECT id FROM test_acc;                                   -- 1 (counter reset)

-- DROP (DDL): removes data AND structure, cannot be rolled back
DROP TABLE test_acc;
-- SELECT * FROM test_acc;  -> ERROR 1146: table doesn't exist

-- ================= TASK 4: INSERT / UPDATE / DELETE / filters =================
INSERT INTO account (account_number, account_type, rm_id, balance, opened_date)
VALUES ('ACC100000007','SAVINGS',1, 90000.00, CURDATE() - INTERVAL 10 DAY);

UPDATE account SET balance = balance + 5000 WHERE account_number = 'ACC100000005';

DELETE FROM account WHERE account_number = 'ACC100000007';  -- no holders linked, so no FK conflict

-- Accounts opened in last quarter (3 months) with balance above a threshold
SELECT account_number, account_type, balance, opened_date
FROM account
WHERE opened_date BETWEEN CURDATE() - INTERVAL 3 MONTH AND CURDATE()
  AND balance > 50000
  AND (account_type IN ('SAVINGS','CURRENT') OR account_number LIKE 'ACC1000000%');

-- Customers whose name starts with 'A'
SELECT * FROM customer WHERE full_name LIKE 'A%';

-- ================= TASK 5: ALTER TABLE =================
-- (a) Add column. Effect: all existing rows get the default 'PENDING'.
ALTER TABLE customer
  ADD COLUMN kyc_status VARCHAR(10) NOT NULL DEFAULT 'PENDING';
UPDATE customer SET kyc_status = 'VERIFIED' WHERE customer_id IN (1,2);

-- (b) Rename table. Effect: data, keys and FKs are preserved; FKs follow automatically,
--     but any queries/views using the old name break.
RENAME TABLE relationship_manager TO rel_manager;

-- (c) Drop a constraint. Effect: negative balances are now accepted (CHECK no longer enforced).
ALTER TABLE account DROP CHECK chk_balance;
-- Test: INSERT ... balance = -100 now succeeds. Restore it with:
-- ALTER TABLE account ADD CONSTRAINT chk_balance CHECK (balance >= 0);
