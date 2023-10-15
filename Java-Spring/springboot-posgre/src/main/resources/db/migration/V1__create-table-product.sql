CREATE TABLE product(
    id INT PRIMARY KEY NOT NULL UNIQUE,
    name VARCHAR(60) NOT NULL,
    price_in_cents INT NOT NULL
);