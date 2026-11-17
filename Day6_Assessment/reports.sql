-- ============================================================
-- Set 1 - Retail Sales Reporting
-- File: reports.sql   (MySQL 8)
-- Run section 0 first, then any query below.
-- ============================================================

-- ------------------------------------------------------------
-- 0. Schema and sample data
-- ------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS retail_db;
USE retail_db;

DROP TABLE IF EXISTS payments, order_items, orders, products, customers;

CREATE TABLE customers (
  customer_id    INT PRIMARY KEY,
  first_name     VARCHAR(50),
  last_name      VARCHAR(50),
  city           VARCHAR(50),
  signup_date    DATE,
  loyalty_points INT NULL
);

CREATE TABLE products (
  product_id   INT PRIMARY KEY,
  product_name VARCHAR(100),
  category     VARCHAR(50),
  price        DECIMAL(10,2)
);

CREATE TABLE orders (
  order_id    INT PRIMARY KEY,
  customer_id INT,
  order_date  DATE,
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
  item_id    INT PRIMARY KEY AUTO_INCREMENT,
  order_id   INT,
  product_id INT,
  quantity   INT,
  unit_price DECIMAL(10,2),
  FOREIGN KEY (order_id)   REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE payments (
  payment_id   INT PRIMARY KEY,
  order_id     INT,
  amount       DECIMAL(10,2),
  method       VARCHAR(20),
  payment_date DATE,
  FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

INSERT INTO customers VALUES
(1,'Asha','Rao','Chennai','2025-01-10',100),
(2,'Bala','Kumar','Chennai','2025-02-15',NULL),
(3,'Chitra','Devi','Mumbai','2025-03-01',50),
(4,'Dev','Patel','Mumbai','2025-03-20',NULL),
(5,'Esha','Singh','Delhi','2025-04-05',150),
(6,'Farhan','Ali','Delhi','2025-04-10',NULL),
(7,'Gita','Iyer','Chennai','2025-04-12',0);

INSERT INTO products VALUES
(1,'Laptop','Electronics',50000),
(2,'Headphones','Electronics',2000),
(3,'T-Shirt','Apparel',500),
(4,'Jeans','Apparel',1500),
(5,'Notebook','Stationery',100),
(6,'Pen Pack','Stationery',50);

INSERT INTO orders VALUES
(101,1,'2025-01-15'),(102,1,'2025-01-20'),(103,1,'2025-02-10'),(104,1,'2025-02-25'),
(105,2,'2025-01-15'),(106,2,'2025-02-10'),
(107,3,'2025-02-10'),(108,3,'2025-03-05'),(109,3,'2025-03-12'),(110,3,'2025-03-15'),
(111,4,'2025-03-20'),
(112,5,'2025-03-20');

INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
(101,1,1,50000),
(102,3,2,500),
(103,2,1,2000),(103,5,3,100),
(104,4,1,1500),
(105,2,2,2000),
(106,3,4,500),
(107,4,2,1500),
(108,5,5,100),
(109,2,1,2000),
(110,3,1,500),(110,5,2,100),
(111,1,1,50000),
(112,4,1,1500),(112,3,2,500);

-- Orders 104, 109 and 112 are intentionally left unpaid
INSERT INTO payments VALUES
(1,101,50000,'Card','2025-01-15'),
(2,102,1000,'UPI','2025-01-20'),
(3,103,2300,'UPI','2025-02-10'),
(4,105,4000,'Card','2025-01-15'),
(5,106,2000,'COD','2025-02-11'),
(6,107,3000,'UPI','2025-02-10'),
(7,108,500,'UPI','2025-03-05'),
(8,110,700,'COD','2025-03-16'),
(9,111,50000,'Card','2025-03-20');

-- ============================================================
-- Task 1: Reporting queries
-- ============================================================

-- Q1. Top 5 products by revenue
SELECT p.product_id,
       p.product_name,
       SUM(oi.quantity * oi.unit_price) AS revenue
FROM order_items oi
JOIN products p ON p.product_id = oi.product_id
GROUP BY p.product_id, p.product_name
ORDER BY revenue DESC
LIMIT 5;

-- Q2. Month-wise sales
SELECT DATE_FORMAT(o.order_date, '%Y-%m') AS sales_month,
       COUNT(DISTINCT o.order_id)          AS total_orders,
       SUM(oi.quantity * oi.unit_price)    AS total_sales
FROM orders o
JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY sales_month;

-- Q3. Customers with more than three orders (HAVING)
SELECT c.customer_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       COUNT(o.order_id)                      AS order_count
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
HAVING COUNT(o.order_id) > 3;

-- Q4. Average basket value per city
SELECT t.city,
       COUNT(*)                    AS orders,
       ROUND(AVG(t.order_total),2) AS avg_basket_value
FROM (
    SELECT o.order_id, c.city,
           SUM(oi.quantity * oi.unit_price) AS order_total
    FROM orders o
    JOIN customers c    ON c.customer_id = o.customer_id
    JOIN order_items oi ON oi.order_id   = o.order_id
    GROUP BY o.order_id, c.city
) t
GROUP BY t.city
ORDER BY avg_basket_value DESC;

-- Q5. Orders without payment (LEFT JOIN)
SELECT o.order_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       o.order_date,
       SUM(oi.quantity * oi.unit_price)       AS amount_due
FROM orders o
JOIN customers c    ON c.customer_id = o.customer_id
JOIN order_items oi ON oi.order_id   = o.order_id
LEFT JOIN payments p ON p.order_id   = o.order_id
WHERE p.payment_id IS NULL
GROUP BY o.order_id, c.first_name, c.last_name, o.order_date
ORDER BY o.order_id;

-- Q6. Customers with no orders
SELECT c.customer_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       c.city
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.customer_id
WHERE o.order_id IS NULL;

-- Q7. Product-category revenue share
SELECT p.category,
       SUM(oi.quantity * oi.unit_price) AS category_revenue,
       ROUND(100 * SUM(oi.quantity * oi.unit_price) /
             (SELECT SUM(quantity * unit_price) FROM order_items), 2) AS revenue_share_pct
FROM order_items oi
JOIN products p ON p.product_id = oi.product_id
GROUP BY p.category
ORDER BY category_revenue DESC;

-- Q8. Day with the highest sales
SELECT o.order_date,
       COUNT(DISTINCT o.order_id)       AS orders,
       SUM(oi.quantity * oi.unit_price) AS day_sales
FROM orders o
JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY o.order_date
ORDER BY day_sales DESC
LIMIT 1;

-- ============================================================
-- Task 2: Functions
-- ============================================================

-- Task 2. Customer-friendly output columns
SELECT UPPER(CONCAT(c.first_name, ' ', c.last_name))     AS customer_name,
       CONCAT('Order #', o.order_id)                     AS order_ref,
       DATE_FORMAT(o.order_date, '%d %b %Y')             AS order_date_fmt,
       DATEDIFF('2025-04-01', o.order_date)              AS days_since_order,
       ROUND(SUM(oi.quantity * oi.unit_price) * 1.18, 2) AS total_with_gst
FROM orders o
JOIN customers c    ON c.customer_id = o.customer_id
JOIN order_items oi ON oi.order_id   = o.order_id
GROUP BY o.order_id, c.customer_id, c.first_name, c.last_name, o.order_date
ORDER BY o.order_id
LIMIT 5;

-- ============================================================
-- Task 3: NULL behaviour
-- ============================================================

-- Task 3. NULL behaviour in COUNT, SUM and AVG
SELECT COUNT(*)                          AS count_star,
       COUNT(loyalty_points)             AS count_column,
       SUM(loyalty_points)               AS sum_points,
       AVG(loyalty_points)               AS avg_ignoring_null,
       AVG(COALESCE(loyalty_points, 0))  AS avg_treating_null_as_zero
FROM customers;

-- ============================================================
-- Task 4: WHERE vs HAVING
-- ============================================================

-- Task 4. WHERE vs HAVING
SELECT c.customer_id,
       CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
       COUNT(o.order_id)                      AS orders_since_feb
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
WHERE o.order_date >= '2025-02-01'      -- filters ROWS before grouping
GROUP BY c.customer_id, c.first_name, c.last_name
HAVING COUNT(o.order_id) >= 2;          -- filters GROUPS after aggregation
