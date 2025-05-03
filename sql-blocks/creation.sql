create table products (
    product_id NUMBER Primary key,
    product_name varchar2(30),
    product_price NUMBER,
    category_id NUMBER REFERENCES Categories
)

create table categories(
    category_id NUMBER Primary key,
    category_name varchar2(30)
)

