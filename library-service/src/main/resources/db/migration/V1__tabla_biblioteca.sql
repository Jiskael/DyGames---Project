CREATE TABLE biblioteca (
                            id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                            usuario_id   BIGINT NOT NULL,
                            juego_id     BIGINT NOT NULL,
                            orden_id     BIGINT,
                            adquirido_en DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO biblioteca (usuario_id, juego_id, orden_id, adquirido_en) VALUES
(1, 1, 1, NOW()),
(1, 3, 1, NOW()),
(2, 2, 2, NOW());
