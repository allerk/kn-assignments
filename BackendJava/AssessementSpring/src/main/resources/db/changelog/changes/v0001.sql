CREATE SEQUENCE customers_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE orders_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE order_lines_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE products_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table customers(
     id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('customers_sequence'),
     registration_code VARCHAR(256) NOT NULL,
     full_name VARCHAR(512) NOT NULL,
     email VARCHAR(512) NOT NULL,
     telephone VARCHAR(128) NOT NULL
);

create table orders(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('orders_sequence'),
--     date_of_submission TIME WITHOUT TIME ZONE NOT NULL,
    date_of_submission TIME NOT NULL,
    customer_id BIGINT NOT NULL
);

create table order_lines(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('products_sequence'),
    quantity INTEGER NOT NULL,
    order_id BIGINT NOT NULL
);

create table products(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('orders_sequence'),
    name VARCHAR(256) NOT NULL,
    sku_code VARCHAR(12) NOT NULL,
    unit_price DECIMAL NOT NULL
);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_order_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE;

ALTER TABLE ONLY order_lines
    ADD CONSTRAINT fk_order_lines_order_id FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE;

