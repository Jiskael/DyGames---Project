CREATE TABLE carrito (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         juego_id   BIGINT NOT NULL,
                         precio     DOUBLE NOT NULL,
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);