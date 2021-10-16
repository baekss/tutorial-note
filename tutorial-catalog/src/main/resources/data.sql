create table catalog (
  id int primary key,
  product_id varchar,
  product_name varchar,
  stock int,
  unit_price int,
  create_at datetime
);
insert into catalog(id, product_id, product_name, stock, unit_price)
	values(1, 'P-001', 'A', 100, 10000);
insert into catalog(id, product_id, product_name, stock, unit_price)
	values(2, 'P-002', 'B', 50, 20000);
insert into catalog(id, product_id, product_name, stock, unit_price)
	values(3, 'P-003', 'C', 10, 30000);