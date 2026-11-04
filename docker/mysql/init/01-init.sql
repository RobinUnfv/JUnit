-- =============================================
-- Base de datos
-- =============================================
CREATE DATABASE IF NOT EXISTS db_inventory
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE db_inventory;

-- =============================================
-- Tabla Category (según entidad Category.java)
-- =============================================
CREATE TABLE IF NOT EXISTS category (
                                        id          BIGINT       NOT NULL AUTO_INCREMENT,
                                        name        VARCHAR(255) DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Tabla Product (según entidad Product.java)
-- =============================================
CREATE TABLE IF NOT EXISTS product (
                                       id         BIGINT       NOT NULL AUTO_INCREMENT,
                                       name       VARCHAR(255) DEFAULT NULL,
    price      INT          NOT NULL DEFAULT 0,
    account    INT          NOT NULL DEFAULT 0,
    category_id BIGINT      DEFAULT NULL,
    picture    LONGBLOB     DEFAULT NULL,
    PRIMARY KEY (id),
    KEY fk_product_category (category_id),
    CONSTRAINT fk_product_category
    FOREIGN KEY (category_id) REFERENCES category (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
-- Datos de ejemplo: Categorías
-- =============================================
INSERT INTO category (id, name, description) VALUES
                                                 (1, 'Electrónica',  'Dispositivos electrónicos y accesorios'),
                                                 (2, 'Ropa',         'Prendas de vestir para hombre y mujer'),
                                                 (3, 'Hogar',        'Artículos para el hogar y cocina'),
                                                 (4, 'Deportes',     'Equipamiento y ropa deportiva'),
                                                 (5, 'Alimentos',    'Productos alimenticios no perecederos');

-- =============================================
-- Datos de ejemplo: Productos
-- (picture = NULL para no cargar binarios de ejemplo)
-- =============================================
INSERT INTO product (id, name, price, account, category_id, picture) VALUES
                                                                         (1, 'Laptop HP 15"',            2500, 10, 1, NULL),
                                                                         (2, 'Mouse inalámbrico Logitech', 45, 50, 1, NULL),
                                                                         (3, 'Teclado mecánico Redragon',  89, 30, 1, NULL),
                                                                         (4, 'Camiseta algodón básica',    25, 100, 2, NULL),
                                                                         (5, 'Pantalón jean slim',         79, 40, 2, NULL),
                                                                         (6, 'Juego de ollas antiadherente', 120, 15, 3, NULL),
                                                                         (7, 'Licuadora Oster 600W',      150, 20, 3, NULL),
                                                                         (8, 'Balón de fútbol Adidas',     60, 25, 4, NULL),
                                                                         (9, 'Mancuernas 10kg (par)',     180, 12, 4, NULL),
                                                                         (10,'Café molido 500g',           18, 200, 5, NULL);

-- =============================================
-- Usuario dedicado para la aplicación (opcional pero recomendado)
-- =============================================
CREATE USER IF NOT EXISTS 'inventory_user'@'%' IDENTIFIED BY 'inventory_pass';
GRANT ALL PRIVILEGES ON db_inventory.* TO 'inventory_user'@'%';
FLUSH PRIVILEGES;