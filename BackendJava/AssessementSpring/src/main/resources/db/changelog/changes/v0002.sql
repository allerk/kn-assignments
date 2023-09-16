CREATE SEQUENCE order_line_products_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table order_line_products(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('order_line_products_sequence'),
    order_line_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL
);

ALTER TABLE ONLY order_line_products
    ADD CONSTRAINT fk_order_line_products_order_line_id FOREIGN KEY (order_line_id) REFERENCES order_lines (id) ON DELETE CASCADE;

ALTER TABLE ONLY order_line_products
    ADD CONSTRAINT fk_order_line_products_product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;