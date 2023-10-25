CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE phone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE email (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
