CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE workshop2;
CREATE TABLE users
(
id int(11) AUTO_INCREMENT NOT NULL,
username varchar(255),
email varchar(255) UNIQUE,
password varchar(255),
PRIMARY KEY (id)
);
INSERT INTO users (username, email, password) VALUES ('dsa','sadsa','sdaasd');
DELETE FROM users WHERE id = 2;
SELECT * FROM users WHERE id = 3;
UPDATE users SET username = 'username', email = 'mail', password = 'pass' WHERE id = 10;
