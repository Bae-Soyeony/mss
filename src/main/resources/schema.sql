
CREATE TABLE brand
(
    idx             INT AUTO_INCREMENT PRIMARY KEY,
    brand_name       VARCHAR(1)
);

CREATE TABLE category
(
    idx             INT AUTO_INCREMENT PRIMARY KEY,
    category_name    VARCHAR(20)
);

CREATE TABLE product
(
    idx             INT AUTO_INCREMENT PRIMARY KEY,
    brand_name       VARCHAR(1),
    category_name    VARCHAR(20),
    price           INT
);