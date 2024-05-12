DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE categories
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE products
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id INT,
    name        VARCHAR(255),
    price       DECIMAL(10, 2),
    stock       INT,
    available   BOOLEAN,
    image       VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';

ALTER TABLE `undo_log` ADD INDEX `ix_log_created` (`log_created`);

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

INSERT INTO categories VALUES (default, 'clothing');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'T-shirt', 20, 1000000, true, './public/images/t-shirt.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Jeans', 50, 1000000, true, './public/images/jeans.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Dress', 80, 1000000, true, './public/images/dress.jpg');

INSERT INTO categories VALUES (default, 'food');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Bread', 2, 1000000, true, './public/images/bread.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Milk Chocolate', 4, 1000000, true, './public/images/chocolate.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Banana', 1, 1000000, true, './public/images/banana.jpg');

INSERT INTO categories VALUES (default, 'books');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'The Great Gatsby', 15, 1000000, true, './public/images/gatsby.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Harry Potter and the Sorcerer\'s Stone', 10, 1000000, true, './public/images/harry_potter.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'To Kill a Mockingbird', 12, 1000000, true, './public/images/mockingbird.jpg');

INSERT INTO categories VALUES (default, 'sports equipment');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Yoga Mat', 30, 1000000, true, './public/images/yoga_mat.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Dumbbells', 40, 1000000, true, './public/images/dumbbells.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Jump Rope', 10, 1000000, true, './public/images/jump_rope.jpg');

INSERT INTO categories VALUES (default, 'cosmetics');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Lipstick', 15, 1000000, true, './public/images/lipstick.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Foundation', 25, 1000000, true, './public/images/foundation.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Mascara', 12, 1000000, true, './public/images/mascara.jpg');

INSERT INTO categories VALUES (default, 'toys');
SET @last_insert_id = LAST_INSERT_ID();
INSERT INTO products VALUES (default, @last_insert_id, 'Teddy Bear', 20, 1000000, true, './public/images/teddy_bear.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'LEGO Set', 50, 1000000, true, './public/images/lego.jpg');
INSERT INTO products VALUES (default, @last_insert_id, 'Remote Control Car', 30, 1000000, true, './public/images/rc_car.jpg');
