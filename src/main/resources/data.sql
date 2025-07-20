INSERT INTO categoryPersistenceEntity (category_id, category_name, category_description) VALUES
  (1, 'Burgers', 'Hambúrgueres e sanduíches'),
  (2, 'Bebidas', 'Bebidas em geral');

INSERT INTO product (product_id, name, quantity, product_value, available_indicator, description, image_path, category_id) VALUES
  (1, 'Hamburger', 50, 10.00, true, 'Hamburger simples', '/images/hamburger.jpg', 1),
  (2, 'Cheeseburger', 60, 12.00, true, 'Cheeseburger com queijo', '/images/cheeseburger.jpg', 1),
  (3, 'Coca-Cola', 100, 5.00, true, 'Refrigerante lata', '/images/coca.jpg', 2),
  (4, 'Suco de Laranja', 80, 6.00, true, 'Suco natural de laranja', '/images/suco.jpg', 2);

INSERT INTO customer (customer_id, document_number, first_name, last_name, email) VALUES
  (1, '11111111111', 'Joao', 'Silva', 'joao@gmail.com'),
  (2, '22222222222', 'Maria', 'Souza', 'maria@gmail.com');

INSERT INTO orders (order_id, order_datetime, status_order, order_code, total_amount, customer_id) VALUES
  (1, '2024-05-01T10:00:00', 1, 100, 15.00, 1);

INSERT INTO order_product (order_id, product_id, product_quantity, product_total_amount) VALUES
  (1, 1, 1, 10.00),
  (1, 3, 1, 5.00);