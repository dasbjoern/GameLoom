CREATE DATABASE steam;

CREATE TABLE game (
	id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	name VARCHAR(255) UNIQUE NOT NULL,
	logo VARCHAR(255) NOT NULL,
	app_id VARCHAR(255)

);

INSERT INTO game (name,logo) VALUES ('"Kingdom Come: Deliverance II', 'https:\/\/shared.fastly.steamstatic.com\/store_item_assets\/steam\/apps\/1771300\/93e28946c46f09d761bbfab1e17e8c1c4a8323a0\/capsule_sm_120.jpg?t=1738836316');


CREATE TYPE user_role AS ENUM ('user', 'admin');
CREATE TABLE users (
	id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	role user_role NOT NULL
);

INSERT INTO users (username, password, role) VALUES ('bjorn', 'bjorn123','user');
INSERT INTO users (username, password, role) VALUES ('admin', 'admin123','admin');
INSERT INTO users (username, password, role) VALUES ('test', '$2a$10$dw4IN.GXEX9g7GLE/DeLj.H9Zwk6rdUyJHdYOk2YPD..t1HjyFB6u','user');

