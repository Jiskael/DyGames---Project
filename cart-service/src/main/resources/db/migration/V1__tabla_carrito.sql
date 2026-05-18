CREATE TABLE carrito (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         juego_id   BIGINT NOT NULL,
                         precio     DOUBLE NOT NULL,
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO carrito (usuario_id, juego_id, precio) VALUES
(3, 5, 29.99),
(3, 6, 19.99);
