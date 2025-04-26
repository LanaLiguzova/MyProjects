DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

-- Create tables
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username varchar(100) UNIQUE NOT NULL,
	password varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	address varchar(200) NOT NULL
);

CREATE TABLE categories (
	id SERIAL PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE authors (
	id SERIAL PRIMARY KEY,
	name varchar(100)
);

CREATE TABLE books (
	id SERIAL PRIMARY KEY,
	title varchar(100) NOT NULL,
	author_id int REFERENCES authors(id),
	category_id int REFERENCES categories(id),
	description TEXT,
	price decimal(10,2) NOT NULL,
	stock int NOT NULL DEFAULT 0
);

CREATE TABLE order_status(
	id SERIAL PRIMARY KEY,
	name varchar(100)
);

CREATE TABLE orders (
	id SERIAL PRIMARY KEY,
	user_id int REFERENCES users(id),
	order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	status_id int REFERENCES order_status(id)
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id int REFERENCES orders(id),
    book_id int REFERENCES books(id),
    quantity int NOT NULL,
    price decimal(10,2) NOT NULL
);

CREATE TABLE cart_items (
	id SERIAL PRIMARY KEY,
	user_id int REFERENCES users(id),
	book_id int REFERENCES books(id),
	quantity int NOT NULL
);

-- Populate tables
INSERT INTO categories (name) VALUES 
('Fantasy'),
('Fiction'),
('Non-fiction');

INSERT INTO authors (name) VALUES ('J R R Tolkien');

INSERT INTO books (title, author_id, category_id, description, price, stock) VALUES (
'The Hobbit', 1, 1, 'Interesting book', 10.50, 2
);

INSERT INTO users (username, password, email, address) VALUES ('Lana', '123', 'lana@mail.com', 'Texas');






