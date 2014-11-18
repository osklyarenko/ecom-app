BEGIN;

-- Test data

INSERT INTO app_user(app_user_id, login, password, first_name, last_name, email, phone_number)
VALUES(nextval('app_user_id_seq'), 'test1', 'test1', 'f_test1', 'l_test1', 'test1@host.com', '+380981742284'); 

INSERT INTO app_user(app_user_id, login, password, first_name, last_name, email, phone_number)
VALUES(nextval('app_user_id_seq'), 'test2', 'test2', 'f_test2', 'l_test2', 'test2@host.com', '+380981562221'); 

INSERT INTO product (product_id, type, name, price) 
VALUES(nextval('product_id_seq'), 'tyres', 'Continental Contact Premium Pro 2', 4520);

INSERT INTO warehouse(product_id, quantity)
VALUES((SELECT product_id FROM product), 2);

-- COMMIT;
ROLLBACK;
