CREATE TABLE brand
(
    idx        INT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(1) UNIQUE NOT NULL
);

CREATE TABLE category
(
    idx           INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE product
(
    idx          INT AUTO_INCREMENT PRIMARY KEY,
    brand_idx    INT NOT NULL,
    category_idx INT NOT NULL,
    price        INT NOT NULL,
    FOREIGN KEY (brand_idx) REFERENCES brand (idx),
    FOREIGN KEY (category_idx) REFERENCES category (idx)
);