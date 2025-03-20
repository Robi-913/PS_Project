CREATE DATABASE IF NOT EXISTS cosmetics;
USE cosmetics;

-- Table: cosmetic_product (informații despre produse, inclusiv categoria ca text)
CREATE TABLE IF NOT EXISTS cosmetic_product (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                name VARCHAR(100) NOT NULL,
                                                brand VARCHAR(100),
                                                price DECIMAL(10, 2),
                                                size VARCHAR(50),
                                                category VARCHAR(100)
);

-- Table: store
CREATE TABLE IF NOT EXISTS store (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     address VARCHAR(200),
                                     phone VARCHAR(20)
);

-- Table: store_product (tabel de legătură între magazine și produse)
CREATE TABLE IF NOT EXISTS store_product (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             store_id INT NOT NULL,
                                             product_id INT NOT NULL,
                                             quantity INT DEFAULT 0,
                                             FOREIGN KEY (store_id) REFERENCES store(id) ON DELETE CASCADE,
                                             FOREIGN KEY (product_id) REFERENCES cosmetic_product(id) ON DELETE CASCADE
);

-- -----------------------------------------------
-- Inserări exemplu pentru cosmetic_product
-- -----------------------------------------------
INSERT INTO cosmetic_product (name, brand, price, size, category) VALUES
                                                                      ('Moisturizing Cream', 'Nivea', 19.99, '50ml', 'Skincare'),
                                                                      ('Foundation', 'L''Oreal', 29.99, '30ml', 'Makeup'),
                                                                      ('Shampoo', 'Head & Shoulders', 9.99, '200ml', 'Haircare'),
                                                                      ('Perfume', 'Chanel', 89.99, '100ml', 'Fragrances'),
                                                                      ('Nail Polish', 'Essie', 14.99, '15ml', 'Nail Care');

-- -----------------------------------------------
-- Inserări exemplu pentru store
-- -----------------------------------------------
INSERT INTO store (name, address, phone) VALUES
                                             ('Cosmetice Regina', 'Str. Mihai Eminescu, Nr. 10, București', '021-123-4567'),
                                             ('Frumusețe Românească', 'Bulevardul Unirii, Nr. 15, Cluj-Napoca', '0264-987654'),
                                             ('Eleganță și Stil', 'Str. Victoriei, Nr. 8, Timișoara', '0256-321654'),
                                             ('Magazinul de Frumusețe', 'Str. Libertății, Nr. 20, Iași', '0234-567890'),
                                             ('Cosmetice de Lux', 'Bulevardul Carol I, Nr. 5, Constanța', '0241-876543');

-- -----------------------------------------------
-- Inserări exemplu pentru store_product (legătură)
-- -----------------------------------------------
INSERT INTO store_product (store_id, product_id, quantity) VALUES
                                                               (1, 1, 50),  -- Cosmetice Regina are 50 de unități de Moisturizing Cream
                                                               (1, 2, 30),  -- Cosmetice Regina are 30 de unități de Foundation
                                                               (2, 1, 20),  -- Frumusețe Românească are 20 de unități de Moisturizing Cream
                                                               (2, 3, 40),  -- Frumusețe Românească are 40 de unități de Shampoo
                                                               (3, 4, 10),  -- Eleganță și Stil are 10 unități de Perfume
                                                               (4, 5, 25),  -- Magazinul de Frumusețe are 25 de unități de Nail Polish
                                                               (5, 2, 15),  -- Cosmetice de Lux are 15 unități de Foundation
                                                               (5, 4, 5);   -- Cosmetice de Lux are 5 unități de Perfume
