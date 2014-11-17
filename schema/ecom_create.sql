BEGIN;

-- DATA MODEL

-- PRODUCT

CREATE TABLE product (
	product_id INTEGER PRIMARY KEY,
	type VARCHAR(16) NOT NULL,
	name VARCHAR(64) NOT NULL,
	description VARCHAR(256),
	price decimal NOT NULL,
	created_timepoint TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX product_name_idx ON product(name);

ALTER TABLE product ADD CONSTRAINT product_type_check CHECK (type IN ('tyres', 'steering_wheel', 'shock_absorber', 'exhaust_system', 'transmission', 'engine'));

CREATE SEQUENCE product_id_seq;

-- WAREHOUSE

CREATE TABLE warehouse (
	product_id INTEGER PRIMARY KEY,
	quantity INTEGER NOT NULL,
	last_updated_timepoint TIMESTAMP NOT NULL
);

ALTER TABLE warehouse ADD CONSTRAINT warehouse_product_fk FOREIGN KEY(product_id) REFERENCES product(product_id) ON UPDATE CASCADE ON DELETE RESTRICT;

-- USER 

CREATE TABLE app_user (
	app_user_id INTEGER PRIMARY KEY,
	login VARCHAR(32) NOT NULL,
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	email VARCHAR(64) NOT NULL,
	phone_number CHAR(13) NOT NULL,
	registration_timepoint TIMESTAMP NOT NULL 
);

CREATE UNIQUE INDEX app_user_login_idx ON app_user(login);
CREATE UNIQUE INDEX app_user_email_idx ON app_user(email);

CREATE SEQUENCE app_user_id_seq;

-- SALE ORDER

CREATE TABLE sale_order (
	sale_order_id INTEGER PRIMARY KEY,
	user_id INTEGER NOT NULL,
	created_timepoint TIMESTAMP NOT NULL,
	status VARCHAR(16) NOT NULL,
	total decimal NOT NULL
);

ALTER TABLE sale_order ADD CONSTRAINT sale_order_status_check CHECK (status IN ('new', 'verified', 'delivered', 'canceled'));

ALTER TABLE sale_order ADD CONSTRAINT sale_order_app_user_fk FOREIGN KEY(user_id) REFERENCES app_user(app_user_id) ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE SEQUENCE sale_order_id_seq;

-- SALE ORDER ITEM

CREATE TABLE sale_order_item (
	sale_order_item_id INTEGER PRIMARY KEY,
	sale_order_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
	canceled BOOLEAN NOT NULL DEFAULT false
);

ALTER TABLE sale_order_item ADD CONSTRAINT sale_order_item_sale_order_fk FOREIGN KEY(sale_order_id) REFERENCES sale_order(sale_order_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE sale_order_item ADD CONSTRAINT sale_order_item_product_fk FOREIGN KEY(product_id) REFERENCES product(product_id) ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE SEQUENCE sale_order_item_id_seq;

-- DELIVERY

CREATE TABLE delivery (
	delivery_id INTEGER PRIMARY KEY,
	sale_order_id INTEGER NOT NULL,
	delivery_address VARCHAR(256) NOT NULL,
	status VARCHAR(16) NOT NULL,
	expected_delivery_date DATE NOT NULL,
	additional_info TEXT
);

ALTER TABLE delivery ADD CONSTRAINT delivery_status_check CHECK (status IN ('new', 'in_progress', 'delivered', 'canceled'));

ALTER TABLE delivery ADD CONSTRAINT delivery_sale_order_fk FOREIGN KEY(sale_order_id) REFERENCES sale_order(sale_order_id) ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE SEQUENCE delivery_id_seq;

-- COMMIT;
ROLLBACK;

-- PATCHES

BEGIN;

ALTER TABLE app_user ADD COLUMN password VARCHAR(16) NOT NULL;

-- COMMIT;
ROLLBACK;

-- TODO: ADD DEFAULTS FOR DATE FIELDS
-- TODO: ENCODE PASSWORDS


