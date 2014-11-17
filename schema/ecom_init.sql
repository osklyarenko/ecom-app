BEGIN;

INSERT INTO app_user(app_user_id, login, password, first_name, last_name, email, phone_number, registration_timepoint)
VALUES(nextval('app_user_id_seq'), 'test1', 'test1', 'f_test1', 'l_test1', 'test1@host.com', '+380981742284', now()); 

INSERT INTO app_user(app_user_id, login, password, first_name, last_name, email, phone_number, registration_timepoint)
VALUES(nextval('app_user_id_seq'), 'test2', 'test2', 'f_test2', 'l_test2', 'test2@host.com', '+380981562221', now()); 

INSERT INTO product (product_id, type, name, price, created_timepoint) 
VALUES(nextval('product_id_seq'), 'tyres', 'Continental Contact Premium Pro 2', 4520, now());

INSERT INTO warehouse(product_id, quantity, last_updated_timepoint)
VALUES((SELECT product_id FROM product), 2, now());

-- COMMIT;
ROLLBACK;