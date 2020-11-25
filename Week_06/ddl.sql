CREATE TABLE customer(
  id INT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(16) NOT NULL,
  identity_card_no VARCHAR(32),
  phone INT UNSIGNED,
  email VARCHAR(50),
  gender CHAR(1),
  register_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY pk_customerinfid(id)
);

CREATE TABLE product(
  id INT UNSIGNED AUTO_INCREMENT NOT NULL,
  code CHAR(64) NOT NULL,
  product_name VARCHAR(20) NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  description TEXT NOT NULL,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY pk_productid(id)
);


CREATE TABLE order(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  sn BIGINT UNSIGNED NOT NULL,
  customer_id INT UNSIGNED NOT NULL,
  shipping_user VARCHAR(16) NOT NULL,
  province SMALLINT NOT NULL COMMENT '省',
  city SMALLINT NOT NULL COMMENT '市',
  district SMALLINT NOT NULL COMMENT '区',
  address VARCHAR(100) NOT NULL COMMENT '地址',
  payment_method TINYINT NOT NULL COMMENT '1cash，2balance，3alipay，4wechat',
  order_amount DECIMAL(8,2) NOT NULL,
  discount_money DECIMAL(8,2) NOT NULL DEFAULT 0.00,
  shipping_money DECIMAL(8,2) NOT NULL DEFAULT 0.00,
  payment_money DECIMAL(8,2) NOT NULL DEFAULT 0.00,
  shipping_comp_name VARCHAR(10),
  shipping_sn VARCHAR(50),
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  shipping_time DATETIME,
  pay_time DATETIME,
  receive_time DATETIME,
  order_status TINYINT NOT NULL DEFAULT 0,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY pk_orderid(id)
);