DROP TABLE IF EXISTS table_orders;
CREATE TABLE table_orders (
    ORDER_ID INT NOT NULL PRIMARY KEY,
    NAME VARCHAR(255),
    PRICE INT,
    CUSTOMER_ID INT);
INSERT INTO table_orders (ORDER_ID, NAME, PRICE, CUSTOMER_ID)
    VALUES
    (1, 'First Order', 100, 1),
    (2, 'Second Order', 110, 2),
    (3, 'Third Order', 120, 1),
    (4, 'Fourth Order', 130, 1);

DROP TABLE IF EXISTS table_customers;
CREATE TABLE table_customers (
    CUSTOMER_ID INT PRIMARY KEY,
    NAME VARCHAR(255),
    EMAIL_ADDRES VARCHAR(255));
INSERT INTO table_customers VALUES(1, 'Customer', 'email@gmail.com');
INSERT INTO table_customers VALUES(2, 'Other Customer', 'otheremail@gmail.com');

 ALTER TABLE table_orders
    ADD FOREIGN KEY (CUSTOMER_ID)
    REFERENCES table_customers(CUSTOMER_ID)

-- DROP TABLE IF EXISTS table_orders;
-- CREATE TABLE table_orders (
--     ORDER_ID INT PRIMARY KEY,
--     NAME VARCHAR(255),
--     PRICE INT,
--     CUSOMER_ID INT);
-- INSERT INTO table_orders VALUES(1, 'First Order', 100, 1);
-- INSERT INTO table_orders VALUES(2, 'Second Order', 110, 2);

-- DROP TABLE IF EXISTS table_customers;
-- CREATE TABLE table_customers (
--     CUSTOMER_ID INT PRIMARY KEY,
--     NAME VARCHAR(255),
--     EMAIL_ADDRES VARCHAR(255));
-- INSERT INTO table_customers VALUES(1, 'Customer', 'email@gmail.com');
-- INSERT INTO table_customers VALUES(2, 'Other Customer', 'otheremail@gmail.com');
--
--  ALTER TABLE table_orders
--     ADD FOREIGN KEY (CUSOMER_ID)
--     REFERENCES table_customers(customer_ID)