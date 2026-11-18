USE bank_branch;

CREATE TABLE test_acc (id INT AUTO_INCREMENT PRIMARY KEY, note VARCHAR(20));
INSERT INTO test_acc (note) VALUES ('a'),('b'),('c');      


START TRANSACTION;
DELETE FROM test_acc;
SELECT COUNT(*) AS rows_after_delete FROM test_acc;       
ROLLBACK;
SELECT COUNT(*) AS rows_after_rollback FROM test_acc;     
DELETE FROM test_acc;
INSERT INTO test_acc (note) VALUES ('d');
SELECT id FROM test_acc;                                  


START TRANSACTION;
TRUNCATE TABLE test_acc;
ROLLBACK;                                                 
SELECT COUNT(*) AS rows_after_truncate FROM test_acc;      
INSERT INTO test_acc (note) VALUES ('e');
SELECT id FROM test_acc;                                   


DROP TABLE test_acc;


INSERT INTO account (account_number, account_type, rm_id, balance, opened_date)
VALUES ('ACC100000007','SAVINGS',1, 90000.00, CURDATE() - INTERVAL 10 DAY);

UPDATE account SET balance = balance + 5000 WHERE account_number = 'ACC100000005';

DELETE FROM account WHERE account_number = 'ACC100000007'; 


SELECT account_number, account_type, balance, opened_date
FROM account
WHERE opened_date BETWEEN CURDATE() - INTERVAL 3 MONTH AND CURDATE()
  AND balance > 50000
  AND (account_type IN ('SAVINGS','CURRENT') OR account_number LIKE 'ACC1000000%');


SELECT * FROM customer WHERE full_name LIKE 'A%';


ALTER TABLE customer
  ADD COLUMN kyc_status VARCHAR(10) NOT NULL DEFAULT 'PENDING';
UPDATE customer SET kyc_status = 'VERIFIED' WHERE customer_id IN (1,2);


RENAME TABLE relationship_manager TO rel_manager;

ALTER TABLE account DROP CHECK chk_balance;

