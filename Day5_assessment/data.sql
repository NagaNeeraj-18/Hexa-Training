USE bank_branch;

INSERT INTO branch (branch_name, city) VALUES
 ('T. Nagar', 'Chennai'), ('Anna Nagar', 'Chennai'), ('Koramangala', 'Bengaluru');

INSERT INTO relationship_manager (rm_name, branch_id) VALUES
 ('Priya Raman', 1), ('Karthik Subbu', 2), ('Meera Iyer', 3);

INSERT INTO customer (full_name, address_line, city, pincode) VALUES
 ('Arun Kumar', '12 Usman Rd', 'Chennai', '600017'),
 ('Divya Menon', '45 2nd Ave', 'Chennai', '600040'),
 ('Rahul Sharma', '8 80 Feet Rd', 'Bengaluru', '560034'),
 ('Sneha Pillai', '23 Lake View Layout','Bengaluru', '560095'),
 ('Vikram Nair',  '5 Cathedral Rd',     'Chennai',   '600086');

INSERT INTO account (account_number, account_type, rm_id, balance, opened_date) VALUES
 ('ACC100000001','SAVINGS',1, 15000.00, '2023-02-10'),
 ('ACC100000002','CURRENT',2, 250000.00, CURDATE() - INTERVAL 20 DAY),
 ('ACC100000003','SAVINGS',3, 75000.50, CURDATE() - INTERVAL 45 DAY),
 ('ACC100000004','FD', 1, 500000.00, CURDATE() - INTERVAL 70 DAY),
 ('ACC100000005','SAVINGS',2, 2500.00, CURDATE() - INTERVAL 30 DAY),
 ('ACC100000006','RD', 3, 60000.00, CURDATE() - INTERVAL 200 DAY);

INSERT INTO account_holder (account_id, customer_id, is_primary) VALUES
 (1,1,TRUE),(2,2,TRUE),(3,3,TRUE),(4,5,TRUE),(5,4,TRUE),(6,3,TRUE),
 (4,1,FALSE);  
