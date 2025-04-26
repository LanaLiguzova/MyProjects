SELECT * FROM cart_items;

SELECT * FROM order_status;

SELECT * FROM orders;
SELECT * FROM order_items;

SELECT * FROM books;

SELECT * FROM authors;

INSERT INTO cart_items (user_id, book_id, quantity) 
VALUES (1,1,1);

INSERT INTO books (title, author_id,category_id,description,price,stock)
VALUES ('The Lord of the Rings',1,1,'Another great book',20.00,3);

INSERT INTO authors (name) VALUES ('Washington Irving');

INSERT INTO order_status (name) VALUES ('Pending');