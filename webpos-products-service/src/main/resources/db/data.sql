INSERT INTO categories VALUES (default, '3C');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'iPhone 14', 5999, 1000000, true, './public/images/iPhone14.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Watch', 799, 1000000, true, './public/images/watch.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'iMac', 12999, 1000000, true, './public/images/iMac.png');

INSERT INTO categories VALUES (default, 'furniture');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Sofa', 169, 1000000, true, './public/images/sofa.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Chair', 129, 1000000, true, './public/images/chair.jpg');

INSERT INTO categories VALUES (default, 'electrical appliance');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Electric Iron', 399, 1000000, true, './public/images/electric-iron.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Blender', 299, 1000000, true, './public/images/blender.jpg');

INSERT INTO categories VALUES (default, 'luxury');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Handbag', 18999, 1000000, true, './public/images/handbag.jpg');

INSERT INTO categories VALUES (default, 'drinks');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Cola', 3, 1000000, true, './public/images/cola.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Milk', 5, 1000000, true, './public/images/milk.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Red bull', 6, 1000000, true, './public/images/redbull.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Sprite', 3, 1000000, true, './public/images/sprite.png');
