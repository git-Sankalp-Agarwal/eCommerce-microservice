INSERT INTO orders (total_price, order_status) VALUES
(25.99, 'PENDING'),
(49.50, 'CONFIRMED'),
(15.75, 'CANCELLED'),
(30.00, 'PENDING'),
(60.25, 'CONFIRMED'),
(22.10, 'CANCELLED'),
(18.99, 'PENDING'),
(45.60, 'DELIVERED'),
(10.75, 'DELIVERED'),
(55.40, 'PENDING'),
(33.20, 'CONFIRMED'),
(27.80, 'DELIVERED'),
(41.90, 'PENDING'),
(19.99, 'CANCELLED'),
(12.50, 'CONFIRMED');


INSERT INTO order_item (order_id, product_id, quantity) VALUES
(1, 101, 2),
(1, 102, 1),
(2, 103, 3),
(2, 104, 2),
(3, 105, 1),
(4, 106, 4),
(5, 107, 2),
(5, 108, 1),
(6, 109, 5),
(7, 110, 2),
(8, 111, 3),
(9, 112, 1),
(10, 113, 4),
(11, 114, 2),
(12, 115, 3);
